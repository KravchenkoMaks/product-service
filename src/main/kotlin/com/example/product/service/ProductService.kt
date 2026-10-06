package com.example.product.service

import com.example.product.dto.ProductRequest
import com.example.product.dto.ProductResponse
import com.example.product.entity.Product
import com.example.product.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun createProduct(request: ProductRequest): ProductResponse {
        val product = Product(
            name = request.name,
            description = request.description,
            price = request.price
        )
        val savedProduct = productRepository.save(product)
        return toResponse(savedProduct)
    }

    fun getProduct(id: Long): ProductResponse {
        val product = productRepository.findById(id)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product with id $id not found"
                )
            }
        return toResponse(product)
    }

    fun deleteProduct(id: Long) {
        if (!productRepository.existsById(id)) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product with id $id not found"
            )
        }
        productRepository.deleteById(id)
    }

    private fun toResponse(product: Product): ProductResponse =
        ProductResponse(
            id = product.id,
            name = product.name,
            description = product.description,
            price = product.price
        )
}
