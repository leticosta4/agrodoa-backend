package com.labweb.agrodoa_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

import com.labweb.agrodoa_backend.service.AnuncioService;
import com.labweb.agrodoa_backend.service.CausaService;

@SpringBootApplication
@EnableAsync
@PropertySource("classpath:application-secrets.properties")
public class AgrodoaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgrodoaBackendApplication.class, args);
	}

	@Bean
    public CommandLineRunner run(CausaService causaService, AnuncioService anuncioService) {
        return args -> {
            causaService.verificarPrazosEVoluntariosCausas();
			anuncioService.verificarDatasAnuncios();
        };
    }
}
