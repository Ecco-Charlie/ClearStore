package soft.exe.autenticacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.exe.autenticacion.dao.CredentialsDetailsService;
import soft.exe.autenticacion.dto.*;

@RequestMapping("/auth")
@RestController
public class AuthController
{

    @Autowired
    private CredentialsDetailsService credentialsDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute AuthLoginRequest authLoginRequest)
    {
        AuthResponse authResponse = credentialsDetailsService.login(authLoginRequest);

        if (authResponse.getToken() == null)
        {
            return ResponseEntity.status(406).body(authResponse);
        }

        return ResponseEntity.ok(authResponse);

    }


    @PostMapping("/register/cliente")
    public ResponseEntity<?> clienteRegister(@ModelAttribute ClienteRequest clienteRequest)
    {
        return ResponseEntity.ok(credentialsDetailsService.clientRegister(clienteRequest));
    }


    @PostMapping("/register/distribuidor")
    public ResponseEntity<?> distribuidorRegister(@ModelAttribute DistribuidorRequest distribuidorRequest)
    {
        return ResponseEntity.ok(credentialsDetailsService.registerDistribuidor(distribuidorRequest));
    }

}
