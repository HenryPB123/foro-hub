package com.henryPB.foro_hub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//!VER DOCUMENTACION DE SpringDoc y luego buscar "bearer"
@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Foro Hub API")
                        .description("API Rest de la aplicación Foro Hub, que contiene las funcionalidades CRUD de posts.")
                        .contact(new Contact()
                                .name("Equipo Backend")
                                .email("forohub@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://forohub/api/licencia")));
    }
}
