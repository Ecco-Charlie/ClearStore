package soft.exe.autenticacion.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soft.exe.autenticacion.client.UsuariosClient;
import soft.exe.autenticacion.config.jwt.JwtUtils;
import soft.exe.autenticacion.dto.*;
import soft.exe.autenticacion.entity.Credenciales;
import soft.exe.autenticacion.entity.ERole;
import soft.exe.autenticacion.entity.Role;

import java.io.IOException;
import java.util.Optional;

@Service
public class CredentialsDetailsService implements UserDetailsService
{

    @Autowired
    private CredentialsDao credentialsDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsuariosClient usuariosClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Credenciales u = credentialsDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado { " + username + " }"));

        UserDetails userDetails = User
                .withUsername(username)
                .password(u.getPassword())
                .roles(u.getRole().getRol().name())
                .build();

        return userDetails;
    }

    public AuthResponse login(AuthLoginRequest authLoginRequest)
    {

        if (authLoginRequest.getEmail().isEmpty() || authLoginRequest.getPassword().isEmpty())
        {
            return AuthResponse.builder()
                    .message("Campos vacios")
                    .build();
        }

        UserDetails user = null;

        try
        {
            user = this.loadUserByUsername(authLoginRequest.getEmail());

        }catch (UsernameNotFoundException e)
        {
            return AuthResponse.builder()
                    .message("Usuario no encontrado")
                    .build();
        }

        if (!passwordEncoder.matches(
                authLoginRequest.getPassword(),
                user.getPassword()
        ))
        {
            return AuthResponse.builder()
                    .message("Password incorrecta")
                    .build();
        }

        String token = jwtUtils.createToken(user);

        return AuthResponse.builder()
                .message("Login Successful")
                .token(token)
                .build();
    }

    public AuthResponse clientRegister(ClienteRequest clienteRequest)
    {

        Optional<Credenciales> u = credentialsDao.findByEmail(clienteRequest.getEmail());

        if (u.isPresent())
        {
            return AuthResponse.builder()
                    .message("El email ya esta registrado")
                    .build();
        }

        Credenciales newUser = Credenciales.builder()
                .email(clienteRequest.getEmail())
                .password(passwordEncoder.encode(clienteRequest.getPassword()))
                .role(ERole.builder().rol(Role.CLIENTE).build())
                .build();

        credentialsDao.save(newUser);

        String token = jwtUtils.createToken(newUser);

        ClienteDto clienteDto = ClienteDto.builder()
                .nombre(clienteRequest.getNombre())
                .apellidos(clienteRequest.getApellidos())
                .direccion(clienteRequest.getDireccion())
                .telefono(clienteRequest.getTelefono())
                .pais(clienteRequest.getPais())
                .idCredenciales(newUser.getId())
                .build();

        usuariosClient.saveCliente(
                clienteDto,
                "Bearer " + token
        );

        return AuthResponse.builder()
                .message("Registro Successful")
                .token(token)
                .build();
    }

    public AuthResponse registerDistribuidor(DistribuidorRequest distribuidorResponse)
    {

        Optional<Credenciales> u = credentialsDao.findByEmail(distribuidorResponse.getEmail());

        if (u.isPresent())
        {
            return AuthResponse.builder()
                    .message("El email ya esta registrado")
                    .build();
        }

        Credenciales newUser = Credenciales.builder()
                .email(distribuidorResponse.getEmail())
                .password(passwordEncoder.encode(distribuidorResponse.getPassword()))
                .role(ERole.builder().rol(Role.DISTRIBUIDOR).build())
                .build();

        credentialsDao.save(newUser);

        String token = jwtUtils.createToken(newUser);

        DistribuidorDto distribuidorDto = null;

        try
        {
            distribuidorDto = DistribuidorDto.builder()
                    .nombre(distribuidorResponse.getNombre())
                    .telefono(distribuidorResponse.getTelefono())
                    .pais(distribuidorResponse.getPais())
                    .foto(distribuidorResponse.getFoto().getBytes())
                    .idCredenciales(newUser.getId())
                    .build();
        }catch (IOException e)
        {
            System.out.println(e);
            return AuthResponse.builder()
                    .message("Registro incorrecto")
                    .token(token)
                    .build();
        }

        usuariosClient.saveDistribuidor(
                distribuidorDto,
                "Bearer " + token
        );

        return AuthResponse.builder()
                .message("Registro Successful")
                .token(token)
                .build();
    }

}
