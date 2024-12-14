package br.com.recrutamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfig {
    @Bean
    OpenAPI informacoes() {
		return new OpenAPI()
					.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
					.components(new io.swagger.v3.oas.models.Components()
				                    .addSecuritySchemes("bearerAuth", 
				                        new SecurityScheme()
				                            .name("Authorization")
				                            .type(SecurityScheme.Type.HTTP)
				                            .scheme("bearer")
				                            .bearerFormat("JWT")))
					.info(new Info().title("Recrutamento interno RESTFul")
											.version("1.0")
											.license(new License().name("Licença - Estudos")
																  .url("https://github.com/RicarlosJunior")));
	}
}
