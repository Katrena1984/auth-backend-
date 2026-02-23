# Auth Backend API
Spring Boot JWT Authentication Service с HttpOnly cookies

## Описание
REST API для аутентификации и регистрации пользователей с использованием JWT токенов и HttpOnly cookies.

## Технологии
- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **PostgreSQL**
- **Hibernate/JPA**
- **Lombok**
- **Maven**

## 🔧 Функционал
 Регистрация пользователей  
 Вход/выход (Login/Logout)  
 JWT аутентификация  
 HttpOnly cookies (защита от XSS)  
 Refresh токены  
 Роли (ADMIN, EDITOR, VIEWER)  
 BCrypt хэширование паролей  
 Защита от CSRF атак

### Требования:
- Java 17+
- PostgreSQL 14+
- Maven 3.8+

# Порт приложения
server.port=8080

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD_HERE

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# JWT Secret (придумайте свой секретный ключ!)
app.jwt-secret=YOUR_SUPER_SECRET_KEY_HERE
app.jwt-expiration-ms=86400000
