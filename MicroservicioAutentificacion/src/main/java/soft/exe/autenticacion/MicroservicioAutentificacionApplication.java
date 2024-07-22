package soft.exe.autenticacion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import soft.exe.autenticacion.dao.CredentialsDao;
import soft.exe.autenticacion.entity.Credenciales;
import soft.exe.autenticacion.entity.ERole;
import soft.exe.autenticacion.entity.Role;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioAutentificacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioAutentificacionApplication.class, args);
	}


	@Bean
	public CommandLineRunner init(CredentialsDao credentialsDao)
	{
		return args -> {

			ERole role = ERole.builder()
					.rol(Role.DISTRIBUIDOR)
					.build();

			ERole role2 = ERole.builder()
					.rol(Role.CLIENTE)
					.build();

			Credenciales u = Credenciales.builder()
					.email("fisaacromeroreyes@gmail.com")
					.password("$2y$10$rWXN3o2DjPsn66NpIFfcse34WjhgzaeFjlOzadP//.gOECMTUiogO")
					.role(role)
					.build();

			Credenciales u2 = Credenciales.builder()
					.email("fabioisaacrr@gmail.com")
					.password("$2y$10$rWXN3o2DjPsn66NpIFfcse34WjhgzaeFjlOzadP//.gOECMTUiogO")
					.role(role2)
					.build();

			credentialsDao.save(u);
			credentialsDao.save(u2);

		};
	}

}
