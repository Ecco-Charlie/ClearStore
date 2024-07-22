package soft.exe.autenticacion.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import soft.exe.autenticacion.entity.Credenciales;

import java.util.Optional;

@Repository
public interface CredentialsDao extends CrudRepository<Credenciales, Long>
{

    Optional<Credenciales> findByEmail(String email);

}
