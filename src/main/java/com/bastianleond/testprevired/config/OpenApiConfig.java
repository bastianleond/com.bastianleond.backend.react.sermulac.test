package com.bastianleond.testprevired.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.stereotype.Component;

@Component
@OpenAPIDefinition(
        info = @Info(
                title = "Test Tech Previred",
                version = "0.0.1",
                description = """
                        Este proyecto es realizado para una prueba técnica para Sermaluc \n
                        Realizada por Bastián León
                        """
        )
)
public class OpenApiConfig {
}
