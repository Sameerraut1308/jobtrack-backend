# 💼 JobTrack — Job Application & Tracking REST API

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x%20%2F%204.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-blue.svg)](https://spring.io/projects/spring-security)
[![MySQL](https://img.shields.io/badge/Database-MySQL-blue.svg)](https://www.mysql.com/)
[![Swagger](https://img.shields.io/badge/API%20Docs-Swagger%20%2F%20OpenAPI-green.svg)](http://localhost:8080/swagger-ui/index.html)

**JobTrack** is a RESTful backend application for managing job listings, saved jobs, resumes, and job applications. It is built using **Java 21, Spring Boot, Spring Security, Spring Data JPA, and MySQL**, with JWT-based authentication and centralized validation and exception handling.

The project was developed as a **Spring Boot learning and portfolio project**, with an emphasis on clean layered architecture, REST API design, database relationships, authentication, and real-world application workflows.

---

## 🌟 Key Features

### 🔐 Authentication & Security

* Stateless authentication using **JWT (JSON Web Tokens)**.
* Secure password storage using **BCrypt**.
* Protected API endpoints using **Spring Security**.
* Current authenticated user available through the security context.

### 🏢 Company & Job Management

* Create, retrieve, update, and delete company records.
* Create and manage job listings.
* Associate job listings with companies.
* Store job information such as salary, location, description, and posting date.

### 🔍 Job Search

* Search jobs using keywords across the job title and description.
* Filter job listings by location.
* Retrieve jobs belonging to a specific company.

### 📄 Resume Management

* Upload resumes using `MultipartFile`.
* Store uploaded resume files on the server.
* Associate resumes with authenticated users.
* Download and delete uploaded resumes.
* Support for PDF/DOCX resume types.

### 📌 Saved Jobs

* Bookmark jobs for later review.
* View jobs saved by the authenticated user.
* Remove saved jobs.
* Prevent duplicate saved-job entries.

### 📝 Application Tracking

* Apply to available job listings.
* Optionally associate a resume with an application.
* Track application status throughout the hiring process.
* Supported application statuses include:

  * `APPLIED`
  * `ONLINE_ASSESSMENT`
  * `INTERVIEW`
  * `OFFERED`
  * `REJECTED`
  * `WITHDRAWN`

### 🛡️ Validation & Exception Handling

* Request validation using Jakarta Bean Validation.
* Validation annotations such as `@Valid`, `@NotBlank`, `@NotNull`, `@Email`, and `@Positive`.
* Centralized exception handling using `@RestControllerAdvice`.
* Standardized API error responses for validation failures, invalid requests, missing resources, and unexpected errors.

### 📖 API Documentation

* Interactive API documentation using **Springdoc OpenAPI / Swagger UI**.
* JWT Bearer authentication support directly within Swagger UI.

---

## 🏗️ Architecture

JobTrack follows a **layered architecture** that separates API handling, business logic, data access, and domain models.

```text
                    Client
                      │
                      ▼
               ┌─────────────┐
               │ Controllers │
               └──────┬──────┘
                      │
                      ▼
                ┌───────────┐
                │ Services  │
                └─────┬─────┘
                      │
                      ▼
              ┌──────────────┐
              │ Repositories │
              └──────┬───────┘
                     │
                     ▼
                  MySQL
```

Authentication is handled through Spring Security and JWT:

```text
HTTP Request
     │
     ▼
JWT Authentication Filter
     │
     ▼
Security Context
     │
     ▼
Controller → Service → Repository → MySQL
```

### Project Structure

```text
src/main/java/jobtrack
│
├── config/
│   └── SecurityConfig
│
├── controller/
│   ├── ApplicationController
│   ├── CompanyController
│   ├── JobController
│   ├── ResumeController
│   ├── SavedJobController
│   └── UserController
│
├── dto/
│   ├── CompanyRequest
│   ├── ErrorResponse
│   ├── JobRequest
│   ├── LoginRequest
│   └── UserRegisterRequest
│
├── entity/
│   ├── Application
│   ├── Company
│   ├── Job
│   ├── Resume
│   ├── SavedJob
│   └── User
│
├── enums/
│   ├── ApplicationStatus
│   ├── CompanyType
│   └── ResumeType
│
├── exception/
│   ├── BadRequestException
│   ├── GlobalExceptionHandler
│   └── ResourceNotFoundException
│
├── repository/
│   ├── ApplicationRepository
│   ├── CompanyRepository
│   ├── JobRepository
│   ├── ResumeRepository
│   ├── SavedJobRepository
│   └── UserRepository
│
├── security/
│   ├── CurrentUserService
│   ├── JwtAuthenticationFilter
│   └── JwtService
│
└── service/
    ├── ApplicationService
    ├── CompanyService
    ├── JobService
    ├── ResumeService
    ├── SavedJobService
    └── UserService
```

---

## 🗄️ Database Relationships

```text
User (1)
 │
 ├──────────────< (N) Applications >────────────── Job (N) ──────── Company (1)
 │
 ├──────────────< (N) Resumes
 │
 └──────────────< (N) SavedJobs >───────────────── Job
```

### Core Relationships

* A **User** can have multiple resumes.
* A **User** can save multiple jobs.
* A **User** can submit multiple applications.
* A **Job** belongs to a **Company**.
* A **Job** can have multiple applications.
* A **Job** can be saved by multiple users.
* An **Application** can optionally reference a user's resume.

Database-level constraints are used to prevent duplicate applications and duplicate saved jobs for the same user and job.

---

## 🛠️ Tech Stack

| Technology                  | Purpose                          |
| --------------------------- | -------------------------------- |
| **Java 21**                 | Programming language             |
| **Spring Boot**             | Backend framework                |
| **Spring Web MVC**          | REST API development             |
| **Spring Data JPA**         | Database access and ORM          |
| **Spring Security**         | Authentication and authorization |
| **JWT / JJWT**              | Token-based authentication       |
| **BCrypt**                  | Password hashing                 |
| **MySQL**                   | Relational database              |
| **Jakarta Bean Validation** | Request validation               |
| **Springdoc OpenAPI**       | Swagger API documentation        |
| **Maven**                   | Build and dependency management  |

---

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed:

* Java 21 or later
* MySQL Server
* Maven (optional if using the included Maven Wrapper)

---

### 1. Clone the Repository

```bash
git clone <repository-url>
cd JobTrack
```

---

### 2. Create the Database

Open MySQL and create the JobTrack database:

```sql
CREATE DATABASE IF NOT EXISTS jobtrack;
```

---

### 3. Configure Database Connection

Update:

```text
src/main/resources/application.properties
```

with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobtrack
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

> Do not commit real database passwords or other sensitive credentials to the repository.

---

### 4. Run the Application

Using the Maven Wrapper:

**Linux/macOS:**

```bash
./mvnw clean spring-boot:run
```

**Windows:**

```bash
mvnw.cmd clean spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

---

## 📖 API Documentation

Once the application is running, open Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger provides an interactive interface for exploring and testing the available REST endpoints.

### Authentication Flow

1. Register a new user.
2. Log in using the registered credentials.
3. Copy the JWT returned by the login endpoint.
4. Click **Authorize** in Swagger UI.
5. Enter the JWT using the Bearer authentication scheme.
6. You can now test protected endpoints.

---

## 🧪 Example Testing Flow

A typical workflow through Swagger or Postman is:

```text
1. Register User
       ↓
2. Login → Receive JWT
       ↓
3. Authorize using JWT
       ↓
4. Create Company
       ↓
5. Create Job
       ↓
6. Upload Resume
       ↓
7. Apply to Job
       ↓
8. View Applications
       ↓
9. Update Application Status
```

Example endpoints:

```text
POST /api/users
POST /api/users/login

POST /api/companies
POST /api/jobs

POST /api/resumes/upload
POST /api/applications/{jobId}

GET  /api/applications/my
PUT  /api/applications/{id}/status
```

---

## 📋 API Endpoints

| Module             | Method | Endpoint                        | Description                  |
| ------------------ | ------ | ------------------------------- | ---------------------------- |
| **Authentication** | POST   | `/api/users`                    | Register a new user          |
| **Authentication** | POST   | `/api/users/login`              | Authenticate and receive JWT |
| **Companies**      | POST   | `/api/companies`                | Create a company             |
| **Companies**      | GET    | `/api/companies`                | Retrieve companies           |
| **Jobs**           | POST   | `/api/jobs`                     | Create a job listing         |
| **Jobs**           | GET    | `/api/jobs`                     | Retrieve/search job listings |
| **Jobs**           | GET    | `/api/jobs/company/{companyId}` | Retrieve jobs for a company  |
| **Resumes**        | POST   | `/api/resumes/upload`           | Upload a resume              |
| **Resumes**        | GET    | `/api/resumes/download/{id}`    | Download a resume            |
| **Saved Jobs**     | POST   | `/api/saved-jobs/{jobId}`       | Save a job                   |
| **Saved Jobs**     | GET    | `/api/saved-jobs`               | View saved jobs              |
| **Saved Jobs**     | DELETE | `/api/saved-jobs/{jobId}`       | Remove a saved job           |
| **Applications**   | POST   | `/api/applications/{jobId}`     | Apply to a job               |
| **Applications**   | GET    | `/api/applications/my`          | View the user's applications |
| **Applications**   | PUT    | `/api/applications/{id}/status` | Update application status    |

---

## 🔒 Security Considerations

JobTrack uses several mechanisms to protect user data:

* Passwords are hashed using **BCrypt** rather than stored as plain text.
* Authentication is handled through **stateless JWTs**.
* Protected endpoints require authentication.
* The authenticated user is obtained from Spring Security's security context.
* Database constraints prevent duplicate applications and saved jobs.
* Request validation prevents invalid input from reaching the service layer.

For deployment, sensitive configuration such as database credentials and JWT signing keys should be supplied through environment variables or external configuration rather than committed to source control.

---

## 🎯 Learning Objectives

This project was developed to gain practical experience with:

* Building RESTful APIs with Spring Boot
* Designing layered backend architectures
* Working with Spring Data JPA and MySQL
* Modeling entity relationships
* Implementing JWT authentication with Spring Security
* Hashing passwords securely using BCrypt
* Using DTOs for API requests
* Implementing Bean Validation
* Designing centralized exception handling
* Handling multipart file uploads
* Implementing real-world business workflows
* Documenting APIs with Swagger / OpenAPI

---

## 📌 Project Status

**Current status: Core backend functionality implemented.**

The project covers authentication, company and job management, job searching, resume management, saved jobs, application tracking, validation, and centralized exception handling.

Further improvements can include more comprehensive automated testing, stronger role-based authorization, externalized security configuration, and additional production-oriented infrastructure.

---

## 👨‍💻 Purpose

JobTrack was built as a **learning and portfolio project** to develop practical backend development skills using the Spring Boot ecosystem and to gain experience designing and implementing a complete REST API around a realistic application domain.
