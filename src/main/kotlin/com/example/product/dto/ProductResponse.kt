package com.example.product.dto

import java.math.BigDecimal

data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val price: BigDecimal
)
