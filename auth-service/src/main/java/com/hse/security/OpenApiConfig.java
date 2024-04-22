package com.hse.security;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Aimprovement API")
                        .description("")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Tasbauova Dayana")
                                .url("https://t.me/aurum_tell")
                                .email("datasbauova@edu.hse.ru"))
                );
    }
}
