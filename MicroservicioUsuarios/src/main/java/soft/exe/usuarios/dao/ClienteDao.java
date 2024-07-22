package soft.exe.usuarios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import soft.exe.usuarios.entity.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long>
{



}
