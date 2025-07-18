package com.labweb.agrodoa_backend.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;

import com.labweb.agrodoa_backend.service.contas.ContaDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired private JwtFilter jwtFilter;
    @Autowired private ContaDetailsService contaDetailsService;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .cors(cors -> {})
            .authorizeHttpRequests(auth -> auth

            //endpoints publicos
            .requestMatchers(
                "/auth/login", //ok 3 tipos de conta  -- mas ta funcionando com inativo
                            "/administradores", //ok
                            "/causas", //ok
                            "/causas/*", //ok
                            "/estados", //ok
                            "/estados/*/cidades", //ok
                            "/usuarios/cadastrar_usuario",
                            ////"/usuarios/reativar_conta", //vai ter que fazer o login dnv dps que reativar - so se der tempo - so se der tempo
                            "/usuarios/ver_perfil/*", //ok
                            "/anuncios", //ok
                            "/anuncios/*", //ok

                            "/v3/api-docs/**",
                            "/v3/api-docs/swagger-config",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
            ).permitAll()

            //endpoints adm
            .requestMatchers(HttpMethod.GET, "/usuarios", "/denuncias", "/administradores/*/meu_perfil").hasRole("ADMINISTRADOR") //ok
            .requestMatchers(HttpMethod.PATCH,
            "/denuncias/*/aprovar", //ok
                        "/denuncias/*/reprovar", //ok
                        "/causas/*/aprovar_criacao_causa", //testar
                        "causas/*/rejeitar_criacao_causa").hasRole("ADMINISTRADOR") //testar

            //endpoints CONTA geral
            .requestMatchers(HttpMethod.GET, "/auth/logout").hasAnyRole("ADMINISTRADOR", "FORNECEDOR", "BENEFICIARIO") //ok 3 tipos de conta
            .requestMatchers(HttpMethod.POST, "/causas/criar_causa").hasAnyRole("ADMINISTRADOR", "FORNECEDOR", "BENEFICIARIO") //ok
            .requestMatchers(HttpMethod.PATCH, "/causas/*/concluir").hasAnyRole("ADMINISTRADOR", "FORNECEDOR", "BENEFICIARIO") //falta no front

            //endpoints USER geral
            .requestMatchers(HttpMethod.GET,
            "/usuarios/meu_perfil", //ok
                        "/usuarios/meu_perfil/minhas_causas", //falta no front
                        "/usuarios/meu_perfil/minhas_causas_voluntarias").hasAnyRole("FORNECEDOR", "BENEFICIARIO") //falta no front
            
            .requestMatchers(HttpMethod.POST,
            "/usuarios/ver_perfil/*/denunciar", //ok
                        "/usuarios/ver_perfil/*/avaliar",  //ok
                        "/causas/*/virar_voluntario").hasAnyRole("FORNECEDOR", "BENEFICIARIO") //testar

            .requestMatchers(HttpMethod.PUT, "/usuarios/meu_perfil/editar").hasAnyRole("FORNECEDOR", "BENEFICIARIO") //falta no front
            
            .requestMatchers(HttpMethod.PATCH,
                "/usuarios/meu_perfil/requerir_tipo_perfil", //falta no front
                            "/usuarios/meu_perfil/desativar_conta").hasAnyRole("FORNECEDOR", "BENEFICIARIO") //falta no front

            //endpoints fornecedor
            .requestMatchers(HttpMethod.GET, "/anuncios/*/negociacoes/listar").hasRole("FORNECEDOR") //testar
            .requestMatchers(HttpMethod.POST,
                "/anuncios/criar_anuncio", //ok
                            "/anuncios/criar_anuncio/criar_produto", //ok
                            "/anuncios/*/negociacoes/listar").hasRole("FORNECEDOR") //ok

            .requestMatchers(HttpMethod.PUT, "/anuncios/*/editar").hasRole("FORNECEDOR") //testar
            .requestMatchers(HttpMethod.PATCH, "/anuncios/*/negociacoes/*/aceitar").hasRole("FORNECEDOR") //testar
            .requestMatchers(HttpMethod.PATCH,
                            "/anuncios/*/cancelar",
                            "anuncios/*/negociacoes/*/cancelar").hasAnyRole("FORNECEDOR", "ADMINISTRADOR") //ok

            //endpoints beneficiario
            .requestMatchers(HttpMethod.GET,
                "/usuarios/meu_perfil/meus_salvos",  //ok
                            "/usuarios/meu_perfil/minhas_negociacoes").hasRole("BENEFICIARIO") //falta no front

            .requestMatchers(HttpMethod.POST,
                            "/anuncios/*/salvar", //ok
                            "/anuncios/*/iniciar_negociacao").hasRole("BENEFICIARIO") //testar

            .anyRequest().authenticated()
            )

            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //usar token em vez de session
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(contaDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));  //isso meio que substitui o CrossOrigins em cada controller > colocar a do frontend real depois
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    //isso ai do cors talvez seja melhor mudar dps ja que a aplicação vai subir >> config.setAllowedOrigins(List.of("https://seu-front-end.com"));
}
