package com.aluracursos.forohub.infra.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    // 1. Autenticación (Público)
                    req.requestMatchers(HttpMethod.POST, "/auth").permitAll();
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    // Primero lo más específico (Cerrar)
                    // Luego las acciones por Rol
                    req.requestMatchers(HttpMethod.POST, "/topicos").hasAuthority("ESTUDIANTE");
                    req.requestMatchers(HttpMethod.PUT, "/topicos/*").hasAuthority("ESTUDIANTE");
                    req.requestMatchers(HttpMethod.DELETE, "/topicos/*").hasAuthority("INSTRUCTOR");

                    req.requestMatchers(HttpMethod.GET, "/estadisticas").permitAll();
                    req.requestMatchers(HttpMethod.PUT, "/topicos/*/cerrar", "/topicos/*/solucionar", "/soluciones/*").hasAnyAuthority(
                            "ESTUDIANTE", "INSTRUCTOR");

                    // Al final lo general (Listar y Detallar)
                    req.requestMatchers(HttpMethod.GET, "/topicos", "/topicos/*").authenticated();
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(customEntryPoint()))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint customEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");

            String json = String.format(
                    "{\"timestamp\": \"%s\", \"status\": 403, \"error\": \"Forbidden\", \"message\": \"Access Denied\", \"path\": \"%s\"}",
                    java.time.Instant.now(),
                    request.getRequestURI()
            );

            response.getWriter().write(json);
        };
    }
}
