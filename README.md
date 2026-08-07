# 🏦 Banking Management System

A secure RESTful Banking Management System built using Spring Boot, Spring Security, JWT Authentication, and MySQL. This project provides secure banking operations with role-based access control for Admin and User.

---

## 🚀 Features

- User Registration & Login
- JWT Authentication
- BCrypt Password Encryption
- Role-Based Authorization (ADMIN / USER)
- Customer Management
- Account Management
- Deposit Money
- Withdraw Money
- Fund Transfer
- Balance Enquiry
- Transaction History
- Global Exception Handling
- Swagger API Documentation
- Professional Logging

---

## 🛠 Technologies Used

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT (JSON Web Token)
- BCrypt Password Encoder
- Maven
- Swagger (OpenAPI)

---

## 🏗 Architecture

```
Client (Postman / Swagger)
          │
          ▼
     Controller
          │
          ▼
       Service
          │
          ▼
 Repository (JPA)
          │
          ▼
    MySQL Database
```

---

## 📂 Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 ├── entity
 ├── exception
 ├── repository
 ├── security
 └── service
```

---

## 📌 Modules

- User Authentication
- Customer Management
- Account Management
- Transaction Management
- Security (JWT Authentication)

---

## 🔐 Roles

### ADMIN

- Manage Customers
- Create Accounts
- Deposit Money
- Withdraw Money
- Transfer Money
- View Balance
- View Transaction History

### USER

- Deposit Money
- Withdraw Money
- Transfer Money
- View Balance
- View Transaction History

---

## 📌 REST APIs

### User APIs

- POST /api/users/register
- POST /api/users/login

### Customer APIs

- GET /api/customers
- GET /api/customers/{id}
- POST /api/customers
- PUT /api/customers/{id}
- DELETE /api/customers/{id}

### Account APIs

- POST /api/accounts
- POST /api/accounts/{id}/deposit
- POST /api/accounts/{id}/withdraw
- POST /api/accounts/transfer
- GET /api/accounts/{id}/balance

### Transaction APIs

- GET /api/transactions/{accountNumber}

---

## 🔑 Sample Login Request

### Request

```json
{
  "username": "suba",
  "password": "12345"
}
```

### Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## 🔒 Security

- JWT Authentication
- Spring Security
- BCrypt Password Encryption
- Role-Based Authorization

---

## 📖 API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🗄 Database

Database Name

```
banking_management_system
```

### Tables

| Table | Description |
|-------|-------------|
| users | Stores user login credentials |
| customers | Stores customer details |
| accounts | Stores account information |
| transactions | Stores deposit, withdrawal and transfer history |

---

## ▶️ Run the Project

### 1. Clone Repository

```bash
git clone https://github.com/Kharthikram/Banking-Management-System.git
```

### 2. Configure Database

Update your MySQL credentials in:

```
application.properties
```

### 3. Run Project

```bash
mvn spring-boot:run
```

### 4. Open Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🚀 Future Enhancements

- Email Notifications
- SMS Alerts
- Loan Management
- Internet Banking
- Mobile Banking
- Interest Calculation

---

## 📸 Screenshots

- Swagger UI
- Postman API Testing
- MySQL Database

---

## 👨‍💻 Author

**M.kharthikram**

GitHub: https://github.com/Kharthikram

---
⭐ If you found this project useful, consider giving it a Star on GitHub.
