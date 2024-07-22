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
@Table(name = "cliente")
public class Cliente
{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column @Size(max = 16)
    private String nombre;

    @Column @Size(max = 32)
    private String apellidos;

    @Column @Size(max = 60)
    private String direccion;

    @Column @Size(max = 2)
    private String pais;

    @Column @Size(max = 13)
    private String telefono;

    @Column(name = "id_credenciales", unique = true, nullable = false)
    private Long idCredenciales;

}
