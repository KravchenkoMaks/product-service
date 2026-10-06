# Product Service - Kotlin + Spring Boot + Gradle

A toy RESTful Product Web Service built with Kotlin, Spring Boot, and Gradle Kotlin DSL.

## Features

- **REST API** for product management (Create, Read, Delete)
- **HTTP Basic Authentication** with in-memory user details
- **H2 In-Memory Database** for development and testing
- **Swagger/OpenAPI** documentation available at `/swagger-ui.html`
- **Input Validation** with Jakarta Validation annotations
- **Global Exception Handler** for meaningful error responses
- **Layered Architecture**: Controller → Service → Repository
- **Kotlin** with idiomatic patterns (data classes, constructor injection)

## Prerequisites

- JDK 21 or higher
- Gradle (or use the included gradlew)

## Running the Application

### Using Gradle

```bash
./gradlew bootRun
```

Or on Windows:
```bash
gradlew.bat bootRun
```

### Using an IDE

Import the project as a Gradle project in IntelliJ IDEA or another IDE, and run the `ProductServiceApplication` class.

## Default Credentials

- **Username**: `admin`
- **Password**: `password123`

These can be changed in `src/main/resources/application.yml`:

```yaml
app:
  security:
    user: admin
    password: password123
```

> **Note**: For production applications, use environment variables or a secure vault (e.g., HashiCorp Vault, AWS Secrets Manager) instead of hardcoding credentials.

## API Endpoints

All endpoints require HTTP Basic Authentication (except Swagger UI and H2 Console).

### Create Product
```
POST /product
Content-Type: application/json
Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=

{
  "name": "Laptop",
  "description": "A powerful laptop",
  "price": 1299.99
}
```

**Response**: `201 Created`
```json
{
  "id": 1,
  "name": "Laptop",
  "description": "A powerful laptop",
  "price": 1299.99
}
```

### Get Product by ID
```
GET /product/1
Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=
```

**Response**: `200 OK`
```json
{
  "id": 1,
  "name": "Laptop",
  "description": "A powerful laptop",
  "price": 1299.99
}
```

### Delete Product
```
DELETE /product/1
Authorization: Basic YWRtaW46cGFzc3dvcmQxMjM=
```

**Response**: `204 No Content`

## Access Swagger UI

Navigate to: `http://localhost:8080/swagger-ui.html`

The Swagger UI provides an "Authorize" button where you can enter your credentials (admin / password123).

## Access H2 Console

Navigate to: `http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave blank)

## Project Structure

```
product-service/
├── src/
│   ├── main/
│   │   ├── kotlin/com/example/product/
│   │   │   ├── ProductServiceApplication.kt
│   │   │   ├── config/
│   │   │   │   ├── OpenApiConfig.kt
│   │   │   │   └── SecurityConfig.kt
│   │   │   ├── controller/
│   │   │   │   └── ProductController.kt
│   │   │   ├── dto/
│   │   │   │   ├── ProductRequest.kt
│   │   │   │   └── ProductResponse.kt
│   │   │   ├── entity/
│   │   │   │   └── Product.kt
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.kt
│   │   │   ├── repository/
│   │   │   │   └── ProductRepository.kt
│   │   │   └── service/
│   │   │       └── ProductService.kt
│   │   └── resources/
│   │       └── application.yml
│   └── test/
└── build.gradle.kts
```

## Validation Rules

- **name**: Must not be blank
- **price**: Must be greater than 0

Invalid requests return `400 Bad Request` with detailed error messages.

## Error Handling

The application includes a global exception handler that returns meaningful error responses:

- `400 Bad Request`: Validation errors
- `404 Not Found`: Product not found
- `500 Internal Server Error`: Unexpected errors

## Security Notes

### Current Implementation

- HTTP Basic Auth with in-memory user storage
- CSRF protection disabled for REST API
- H2 Console frame options disabled for local development

### Production Recommendations

1. **Never hardcode credentials** in code or YAML files
2. Use environment variables:
   ```kotlin
   var user: String = System.getenv("APP_USER") ?: "admin"
   var password: String = System.getenv("APP_PASSWORD") ?: "password123"
   ```

3. Use a secrets management system:
   - HashiCorp Vault
   - AWS Secrets Manager
   - Azure Key Vault
   - Google Cloud Secret Manager

4. Consider using OAuth 2.0 / OpenID Connect for production APIs

5. Enable CSRF protection for form-based endpoints

6. Use HTTPS in production

7. Store passwords with strong hashing (BCrypt, Argon2)

## Dependencies

- Spring Boot 3.3.0
- Spring Security
- Spring Data JPA
- Hibernate
- H2 Database
- Jakarta Validation
- springdoc-openapi (Swagger 3.0)
- Jackson Kotlin module

## Testing the API

You can test the API using:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **curl**:
  ```bash
  curl -X POST http://localhost:8080/product \
    -H "Content-Type: application/json" \
    -u admin:password123 \
    -d '{
      "name": "Keyboard",
      "price": 99.99
    }'
  ```
- **Postman**: Import the Swagger docs or create requests manually with Basic Auth
- **HTTPie**:
  ```bash
  http --auth admin:password123 POST http://localhost:8080/product \
    name="Mouse" price=29.99
  ```

## License

This is a toy project for educational purposes.
