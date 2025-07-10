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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private ContaDetailsService contaDetailsService;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .cors(cors -> {})
            .authorizeHttpRequests(auth -> auth

            //endpoints publicos
            .requestMatchers(
                "/auth/login",
                            "/usuarios/cadastrar_usuario",
                            "/usuarios/ver_perfil/**",
                            "/anuncios",
                            "/anuncios/**",
                            "/administradores",
                            "/causas",
                            "/causas/{idCausa}",
                            "/error",

                            "/v3/api-docs/**",
                            "/v3/api-docs/swagger-config",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
            ).permitAll()

            //endpoints adm
            .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMINISTRADOR")
            .requestMatchers(HttpMethod.POST, "/causas/criar_causa").hasRole("ADMINISTRADOR")

            //endpoints adm
            .requestMatchers(HttpMethod.PUT, "/usuarios/*/desativar_conta").hasAnyRole("FORNECEDOR", "BENEFICIARIO")

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
        //return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance(); //só um teste
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));  //isso meio que substitui o CrossOrigins em cada controller > colocar a do frontend real depois
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    //isso ai do cors talvez seja melhor mudar dps ja que a aplicação vai subir >> config.setAllowedOrigins(List.of("https://seu-front-end.com"));
}
