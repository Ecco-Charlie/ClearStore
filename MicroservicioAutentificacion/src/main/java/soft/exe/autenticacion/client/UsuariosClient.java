package soft.exe.autenticacion.client;

import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import soft.exe.autenticacion.dto.ClienteDto;
import soft.exe.autenticacion.dto.DistribuidorDto;

@FeignClient(name = "MicroservicioUsuarios", url = "127.0.0.1:8082")
public interface UsuariosClient
{

    @PostMapping("/create/cliente")
    ClienteDto saveCliente(
            @RequestBody ClienteDto clienteDto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    );

    @PostMapping("/create/distribuidor")
    ClienteDto saveDistribuidor(
            @RequestBody DistribuidorDto distribuidorDto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    );

}
