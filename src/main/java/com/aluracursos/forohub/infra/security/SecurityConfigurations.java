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
                    // 1. ACCESO PÚBLICO
                    req.requestMatchers(HttpMethod.POST, "/auth").permitAll();
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/estadisticas").permitAll();

                    // 2. TÓPICOS Y SOLUCIONES
                    req.requestMatchers(HttpMethod.PUT, "/topicos/*/cerrar", "/topicos/*/solucionar", "/soluciones/*")
                            .hasAnyAuthority("ESTUDIANTE", "INSTRUCTOR");
                    req.requestMatchers(HttpMethod.POST, "/topicos").hasAuthority("ESTUDIANTE");
                    req.requestMatchers(HttpMethod.PUT, "/topicos/*").hasAuthority("ESTUDIANTE");
                    req.requestMatchers(HttpMethod.DELETE, "/topicos/*").hasAuthority("INSTRUCTOR");
                    req.requestMatchers(HttpMethod.GET, "/topicos", "/topicos/*").authenticated();

                    // 3. RESPUESTAS
                    req.requestMatchers(HttpMethod.DELETE, "/respuestas/*").hasAuthority("INSTRUCTOR");
                    req.requestMatchers(HttpMethod.PUT, "/respuestas/*").hasAuthority("ESTUDIANTE");
                    req.requestMatchers("/respuestas/**").authenticated();

                    // 4. CURSOS
                    req.requestMatchers(HttpMethod.POST, "/cursos").hasAuthority("INSTRUCTOR");
                    req.requestMatchers(HttpMethod.PUT, "/cursos/*").hasAuthority("INSTRUCTOR");
                    req.requestMatchers(HttpMethod.DELETE, "/cursos/*").hasAuthority("INSTRUCTOR");
                    req.requestMatchers(HttpMethod.GET, "/cursos", "/cursos/*").authenticated();

                    // 5. USUARIOS (Gestión de cuentas)
                    req.requestMatchers(HttpMethod.POST, "/usuarios").hasAuthority("INSTRUCTOR");
                    req.requestMatchers(HttpMethod.DELETE, "/usuarios/*").hasAuthority("INSTRUCTOR");
                    req.requestMatchers(HttpMethod.PUT, "/usuarios/*").hasAnyAuthority("ESTUDIANTE", "INSTRUCTOR");
                    req.requestMatchers("/usuarios/**").authenticated();

                    // 6. PERFILES (Ranking y Detalle de usuario)
                    req.requestMatchers("/perfiles/usuario/**").authenticated();

                    // 7. SEGURIDAD GLOBAL
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
