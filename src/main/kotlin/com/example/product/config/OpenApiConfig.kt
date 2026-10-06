package com.example.product.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes(
                        "basicAuth",
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("basic")
                            .description("HTTP Basic Authentication")
                    )
            )
            .info(
                Info()
                    .title("Product Service API")
                    .version("1.0.0")
                    .description("A toy RESTful Product Web Service built with Kotlin, Spring Boot, and Gradle")
                    .contact(
                        Contact()
                            .name("Product Service")
                            .url("https://example.com")
                    )
            )
    }
}
