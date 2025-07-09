package com.labweb.agrodoa_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@PropertySource("classpath:application-secrets.properties")
public class AgrodoaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgrodoaBackendApplication.class, args);
	}

}
