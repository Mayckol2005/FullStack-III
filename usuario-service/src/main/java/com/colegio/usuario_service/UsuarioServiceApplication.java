package com.colegio.usuario_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsuarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioServiceApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public org.springframework.boot.CommandLineRunner iniciarBaseDeDatos(
			/* Reemplaza 'UsuarioRepository' con el nombre exacto de tu repositorio si es distinto */
			com.colegio.usuario_service.repository.UsuarioRepository repository) {

		return args -> {
			// Verificamos si la tabla está vacía
			if (repository.count() == 0) {
				/* Asegúrate de que tu clase Usuario se llame así y tenga estos setters */
				com.colegio.usuario_service.entity.Usuario admin = new com.colegio.usuario_service.entity.Usuario();

				admin.setRut("11.111.111-1");
				admin.setNombre("Administrador Maestro");
				admin.setEmail("admin@colegio.com");

				// Usamos BCryptPasswordEncoder directamente para no depender de la configuración del Auth-Service
				org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder =
						new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
				admin.setPassword(encoder.encode("admin123"));

				admin.setRol("ADMINISTRADOR"); // Si usas un Enum, cámbialo a Rol.ADMINISTRADOR

				repository.save(admin);

				System.out.println("=========================================");
				System.out.println("USUARIO ADMINISTRADOR CREADO CON ÉXITO");
				System.out.println("Correo: admin@colegio.com");
				System.out.println("Clave: admin123");
				System.out.println("=========================================");
			}
		};
	}
}