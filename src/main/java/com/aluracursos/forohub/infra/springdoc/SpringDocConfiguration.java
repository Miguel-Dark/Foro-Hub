package com.aluracursos.forohub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
                )
                .info(new Info()
                        .title("ForoHub Challenge - API Rest")
                        .description("API Rest para el foro de dudas de Alura, con funcionalidades CRUD de tópicos, gestión de respuestas y control de acceso por roles (Estudiante e Instructor).")
                        .contact(new Contact()
                                .name("Miguel-Dark")
                                .email("apache-dark@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/Miguel-Dark/Foro-Hub")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
    }
}
