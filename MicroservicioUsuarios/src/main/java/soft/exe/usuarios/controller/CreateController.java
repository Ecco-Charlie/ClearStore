package soft.exe.usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soft.exe.usuarios.dao.ClienteDao;
import soft.exe.usuarios.dao.DistribuidorDao;
import soft.exe.usuarios.dto.ClienteDto;
import soft.exe.usuarios.dto.DistribuidorDto;
import soft.exe.usuarios.entity.Cliente;
import soft.exe.usuarios.entity.Distribuidor;

@RestController
@RequestMapping("/create")
public class CreateController
{

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private DistribuidorDao distribuidorDao;

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> clientCreate(@RequestBody ClienteDto clienteDto)
    {
        Cliente newCliente = Cliente.builder()
                .nombre(clienteDto.getNombre())
                .apellidos(clienteDto.getApellidos())
                .direccion(clienteDto.getDireccion())
                .telefono(clienteDto.getTelefono())
                .pais(clienteDto.getPais())
                .idCredenciales(clienteDto.getIdCredenciales())
                .build();
        return ResponseEntity.ok(clienteDao.save(newCliente));
    }

    @PostMapping("/distribuidor")
    public ResponseEntity<Distribuidor> createDistribuidor(@RequestBody DistribuidorDto distribuidorDto)
    {
        Distribuidor newDistribuidor = Distribuidor.builder()
                .nombre(distribuidorDto.getNombre())
                .telefono(distribuidorDto.getTelefono())
                .foto(distribuidorDto.getFoto())
                .pais(distribuidorDto.getPais())
                .idCredenciales(distribuidorDto.getIdCredenciales())
                .build();
        return ResponseEntity.ok(distribuidorDao.save(newDistribuidor));
    }

}
