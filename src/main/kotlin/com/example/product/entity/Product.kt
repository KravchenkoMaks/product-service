package com.example.product.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "products")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column
    var description: String? = null,

    @Column(nullable = false)
    var price: BigDecimal
)
