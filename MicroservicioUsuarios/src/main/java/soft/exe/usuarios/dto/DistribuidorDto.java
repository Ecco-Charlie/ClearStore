package soft.exe.usuarios.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DistribuidorDto
{

    @Size(max = 16)
    private String nombre;

    @Size(max = 13)
    private String telefono;

    @Size(max = 2)
    private String pais;

    private byte[] foto;

    private Long idCredenciales;

}
