# E-Commerce API Test Automation (REST Assured)

This project is an automated API testing framework for an e-commerce backend, built with **Java**, **REST Assured**, and **TestNG**/**JUnit 5**.  
It validates API endpoints for core e-commerce operations such as products, orders, users, and authentication.

## 🛠 Tech Stack
- **Java 17+**
- **Maven**
- **REST Assured** – HTTP request/response validation
- **TestNG** / **JUnit 5** – Test framework
- **Jackson** – JSON serialization/deserialization

## 📌 Features
Reusable Request Builder – Common request specifications for all tests (utils.Builder).

Token-based Authentication – Login request and token reuse (models.LoginRequest, pojo.LoginInfo).

Serialization – Convert Java POJOs to JSON payloads for API requests (e.g., LoginRequest, Order).

Deserialization – Map API JSON responses to Java POJOs for easy validation (CreateProductResponse, LoginResponseInfo).

Data-Driven Testing – Load dynamic test data from JSON files (utils.JsonDataLoader, ecom-data.json).

File Upload Testing – Upload product images 

Schema Validation – Validate API responses with JSON schema.

CRUD Test Coverage – Create, read, update, and delete for products and orders.
