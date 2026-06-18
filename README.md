# Smart Clinic Management System (SCMS)

## Overview

The Smart Clinic Management System (SCMS) is a full-stack healthcare management application developed as part of the IBM Java Development Capstone Project.

The system provides a centralized platform for managing clinic operations, including patient records, doctor information, appointments, and prescriptions. It demonstrates the integration of Spring Boot, MySQL, MongoDB, MVC architecture, REST APIs, and modern software engineering practices.

---

## Project Objectives

* Manage patient information efficiently
* Maintain doctor profiles and availability
* Schedule and track appointments
* Store and retrieve prescription records
* Demonstrate integration of relational and NoSQL databases
* Apply MVC and REST architectural patterns
* Implement scalable backend services using Spring Boot

---

## Architecture

The application follows a three-tier architecture:

### Presentation Layer

* Thymeleaf-based Admin Dashboard
* Thymeleaf-based Doctor Dashboard
* REST API modules for Appointment Management
* REST API modules for Patient Dashboard and Patient Records

### Application Layer

* Spring Boot Controllers
* Service Layer
* Business Logic
* Validation and Processing

### Data Layer

* MySQL Database

  * Patient
  * Doctor
  * Appointment
  * Admin

* MongoDB Database

  * Prescription Documents

---

## Technology Stack

| Component             | Technology          |
| --------------------- | ------------------- |
| Backend               | Spring Boot         |
| Frontend              | Thymeleaf           |
| Database (Relational) | MySQL               |
| Database (NoSQL)      | MongoDB             |
| ORM                   | Spring Data JPA     |
| NoSQL Access          | Spring Data MongoDB |
| API Format            | REST / JSON         |
| Build Tool            | Maven               |
| Version Control       | Git & GitHub        |

---

## Core Modules

### Admin Module

* Manage doctors
* Manage patients
* View appointments
* Monitor clinic operations

### Doctor Module

* View appointments
* Access patient information
* Manage prescriptions

### Patient Module

* View records
* Manage appointments
* Access prescriptions

### Appointment Module

* Create appointments
* Update appointments
* Track appointment history

### Prescription Module

* Store prescription details
* Retrieve prescription history

---

## Repository Structure

```text
java-database-capstone
│
├── app/
│
├── schema-architecture.md
│
├── README.md
│
├── LICENSE
│
└── .gitignore
```

---

## Current Lab Deliverables

### Module 1

* Architecture Design Document
* User Stories (Upcoming)

### Module 2

* Database Design
* JPA Models and Validations

### Module 3

* Sample Data
* Stored Procedures

### Module 4

* Frontend and MVC Integration

### Module 5

* REST APIs
* Docker Deployment
* GitHub Actions CI/CD

### Module 6

* Final Submission

---

## Author

Arnav Mishra

IBM Skills Network – Java Development Capstone Project

---

## License

This project is developed for educational purposes as part of the IBM Skills Network Java Development Capstone Program.
