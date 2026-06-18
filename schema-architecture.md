# Smart Clinic Management System Architecture

## Section 1: Architecture Summary

The Smart Clinic Management System follows a three-tier architecture consisting of the Presentation Layer, Application Layer, and Data Layer. The presentation layer supports both Thymeleaf-based dashboards and REST API modules. Admin and Doctor dashboards use MVC with Thymeleaf templates, while appointment and patient-related modules communicate through REST APIs that exchange JSON data.

The application layer is built using Spring Boot and contains Thymeleaf Controllers, REST Controllers, and a common Service Layer that handles business logic and request processing. The data layer uses MySQL and MongoDB together. MySQL stores structured entities such as Patient, Doctor, Appointment, and Admin records using Spring Data JPA, while MongoDB stores Prescription documents using Spring Data MongoDB. This design provides scalability, maintainability, and separation of concerns.

## Section 2: Numbered Flow

1. Users interact with the AdminDashboard, DoctorDashboard, Appointment, PatientDashboard, or PatientRecord modules.
2. Dashboard requests are routed to Thymeleaf Controllers, while API requests are routed to REST Controllers.
3. Controllers send requests to the Service Layer for processing.
4. The Service Layer applies business logic and communicates with the appropriate repository.
5. MySQL Repositories access the MySQL database for Patient, Doctor, Appointment, and Admin data.
6. MongoDB Repository accesses the MongoDB database for Prescription documents.
7. Data is mapped into JPA entities or MongoDB documents and returned through the Service Layer to the controllers, which then generate HTML pages or JSON responses for users.
