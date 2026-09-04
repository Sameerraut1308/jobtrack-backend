# 💼 JobTrack - Job Application & Tracking REST API

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x%20%2F%204.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-blue.svg)](https://spring.io/projects/spring-security)
[![MySQL](https://img.shields.io/badge/Database-MySQL-blue.svg)](https://www.mysql.com/)
[![Swagger](https://img.shields.io/badge/API%20Docs-Swagger%20%2F%20OpenAPI-green.svg)](http://localhost:8080/swagger-ui/index.html)

A production-ready, secure **Job Board & Application Tracking Backend** built with Java 21, Spring Boot, Spring Security, Spring Data JPA, and MySQL.

---

## 🌟 Key Features

- 🔐 **Authentication & Security:** Stateless JWT (JSON Web Token) authentication with password encryption using BCrypt.
- 🏢 **Company & Job Management:** CRUD operations for companies and job listings with salary, location, and company metadata.
- 🔍 **Job Search & Filters:** Search jobs by keywords (title/description) and filter by location.
- 📄 **Resume Management:** Upload, store, and download PDF/DOCX resumes using `MultipartFile`.
- 📌 **Job Bookmarks (Saved Jobs):** Save jobs for later review.
- 📝 **Application Tracking:** Apply to jobs with attached resumes and track application lifecycle (`APPLIED`, `INTERVIEW`, `OFFERED`, `REJECTED`, `WITHDRAWN`).
- 🛡️ **Validation & Error Handling:** Bean Validation (`@Valid`, `@NotBlank`, `@Email`) and a centralized `@RestControllerAdvice` returning standardized error payloads.
- 📖 **Interactive API Documentation:** Integrated Swagger UI with JWT Bearer authorization support.

---

## 🏗️ Architecture & Database Schema
Users (1) ────< (N) Applications >──── (1) Jobs >──── (1) Companies │ │ ├────< (N) Resumes ───┘ │ └────< (N) SavedJobs >──── (1) Jobs

---

## 🛠️ Tech Stack

- **Backend Framework:** Spring Boot (Web MVC, Data JPA, Security, Validation)
- **Language:** Java 21
- **Database:** MySQL
- **Token Security:** JJWT (`io.jsonwebtoken`)
- **API Documentation:** Springdoc OpenAPI (Swagger UI)
- **Build Tool:** Maven

---

## 🚀 Getting Started

### 1. Prerequisites
- Java 21+ installed
- MySQL Server running

### 2. Database Setup
Create a MySQL database:
CREATE DATABASE IF NOT EXISTS jobtrack;

Update your database credentials in src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/jobtrack
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

### 3. Run the Application
bash:
./mvnw clean spring-boot:run
The application will start on http://localhost:8080.

📖 API Documentation & Testing
Access the interactive Swagger UI directly in your browser: 👉 http://localhost:8080/swagger-ui/index.html

Quick Testing Flow:
Register a user: POST /api/users
Login to get JWT: POST /api/users/login
Click the Authorize button in Swagger UI and paste your JWT token.
Create Company & Post Job: POST /api/companies, POST /api/jobs
Upload Resume & Apply: POST /api/resumes/upload, POST /api/applications/{jobId}
Track Applications: GET /api/applications/my


📋 API Endpoints Summary
Module	Method	Endpoint	Description
Auth	POST	/api/users	Register a new user
Auth	POST	/api/users/login	Login and receive JWT token
Companies	POST / GET	/api/companies	Create / List companies
Jobs	POST / GET	/api/jobs	Post jobs / Search with ?keyword=&location=
Resumes	POST	/api/resumes/upload	Upload resume file (multipart/form-data)
Resumes	GET	/api/resumes/download/{id}	Download uploaded resume
Saved Jobs	POST / GET	/api/saved-jobs/{jobId}	Bookmark job / Get saved jobs
Applications	POST	/api/applications/{jobId}	Apply to a job with optional resume
Applications	GET	/api/applications/my	View my job applications
Applications	PUT	/api/applications/{id}/status	Update application status
