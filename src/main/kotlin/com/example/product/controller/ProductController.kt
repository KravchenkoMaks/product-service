package com.example.product.controller

import com.example.product.dto.ProductRequest
import com.example.product.dto.ProductResponse
import com.example.product.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "Product management API")
@SecurityRequirement(name = "basicAuth")
class ProductController(
    private val productService: ProductService
) {
    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details")
    @ApiResponse(
        responseCode = "201",
        description = "Product created successfully",
        content = [Content(schema = Schema(implementation = ProductResponse::class))]
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid input data"
    )
    fun createProduct(@Valid @RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        val response = productService.createProduct(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID", description = "Retrieves a product using its ID")
    @ApiResponse(
        responseCode = "200",
        description = "Product found",
        content = [Content(schema = Schema(implementation = ProductResponse::class))]
    )
    @ApiResponse(
        responseCode = "404",
        description = "Product not found"
    )
    fun getProduct(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        val response = productService.getProduct(id)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID")
    @ApiResponse(
        responseCode = "204",
        description = "Product deleted successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Product not found"
    )
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }
}
