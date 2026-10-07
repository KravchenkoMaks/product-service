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

- JDK 17 or higher
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


The following settings can be changed in `/.config/application.yml`:

```yaml
spring:
  datasource:
    url: newurl
    username: newusername
    password: newpassword

app:
  security:
    user: newuser
    password: newpassword
```

This is a toy project for educational purposes.
