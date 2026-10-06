package com.example.product.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "app.api")
data class OpenApiProperties(
    val version: String,
    val title: String,
    val description: String,
)

@Configuration
class OpenApiConfig(private val properties: OpenApiProperties) {
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
                    .title(properties.title)
                    .version(properties.version)
                    .description(properties.description)
                    .contact(
                        Contact()
                            .name("Product Service")
                            .url("https://example.com")
                    )
            )
    }
}