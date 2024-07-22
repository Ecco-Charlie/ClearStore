package soft.exe.usuarios.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "distribuidor")
public class Distribuidor
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @Size(max = 16)
    private String nombre;

    @Column @Size(max = 13)
    private String telefono;

    @Column @Size(max = 2)
    private String pais;

    @Column(length = 16777215)
    private byte[] foto;

    @Column(name = "id_credenciales", unique = true, nullable = false)
    private Long idCredenciales;

}
