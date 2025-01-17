package soft.exe.autenticacion.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthLoginRequest
{

    @Email
    private String email;

    private String password;

}
