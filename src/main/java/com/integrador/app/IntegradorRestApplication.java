package com.integrador.app;


import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Integrador API", version = "2.0",description = "API chatbot"))
public class IntegradorRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegradorRestApplication.class, args);
    }

}
