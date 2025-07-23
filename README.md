# 🏥 Healthcare Management System – Backend (Spring Boot)

This is the **backend service** of the Healthcare Management System, built with **Spring Boot** and **MySQL**. It provides secure RESTful APIs for managing doctors, patients, appointments, payments, and users with role-based access and JWT authentication.

## 🚀 Tech Stack

- **Framework:** Spring Boot
- **Database:** MySQL
- **Security:** Spring Security + JWT
- **Payment Gateway:** Stripe API
- **Build Tool:** Maven

---

## 🌟 Key Features

- 🔐 **Authentication & Authorization**
  - Secure login using **JWT Tokens**
  - Role-based access for **Admin**, **Doctor**, and **Patient**
  - Spring Security configuration for route protection

- 🧑‍⚕️ **Doctor Management**
  - Full CRUD operations (Create, Read, Update, Delete)
  - Endpoint validations and service-layer logic

- 🧍‍♂️ **Patient Management**
  - Patient registration and profile management
  - Medical history and record updates

- 📅 **Appointment Management**
  - Patients can **create** and **delete** appointments
  - Admins can **view, assign**, and **manage all** appointments
  - Conflict checks and scheduling validations

- 👨‍💼 **Admin Control**
  - Admin can manage users, doctors, patients, and appointments
  - Global system access with security constraints

- 💳 **Stripe Payment Integration**
  - Integrated Stripe API for secure payments
  - Handles billing for appointments

- 📄 **RESTful API Design**
  - Follows REST best practices with clean controller-service-repository architecture
  - Easily consumable endpoints for the Angular frontend

- 🔒 **Security**
  - Spring Security configuration with JWT filters
  - Passwords encrypted using BCrypt
  - CORS handling for frontend requests

---
