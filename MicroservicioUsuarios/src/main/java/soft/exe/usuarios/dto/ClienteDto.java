package soft.exe.usuarios.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteDto
{

    @Size(max = 16)
    private String nombre;

    @Size(max = 32)
    private String apellidos;

    @Size(max = 60)
    private String direccion;

    @Size(max = 2)
    private String pais;

    @Size(max = 13)
    private String telefono;

    private Long idCredenciales;
}
