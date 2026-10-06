package com.example.product.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class ProductRequest(
    @field:NotBlank(message = "Product name is required")
    val name: String,

    val description: String? = null,

    @field:Positive(message = "Product price must be greater than 0")
    val price: BigDecimal
)
