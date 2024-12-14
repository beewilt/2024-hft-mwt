package de.hft.moderndistributedsystemshft24project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("org.openapitools.model")
@SpringBootApplication
public class ModernDistributedSystemsHft24ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModernDistributedSystemsHft24ProjectApplication.class, args);
	}

}
