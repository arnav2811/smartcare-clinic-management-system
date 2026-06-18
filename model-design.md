# Smart Clinic Management System - Model Design with Validations

## Overview

This document defines the Java model classes used in the Smart Clinic Management System. The models represent the core entities of the application and demonstrate how JPA, Hibernate Validator, Jackson, and Spring Data MongoDB annotations are used to provide persistence, validation, security, and data relationships.

---

# Admin Model

```java
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // Getters and Setters
}
```

### Features

* Uses JPA entity mapping
* Auto-generated primary key
* Username is required
* Password is hidden from API responses

---

# Doctor Model

```java
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    private String specialty;

    @Email
    @NotNull
    private String email;

    @Size(min = 6)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Pattern(regexp = "\\d{10}")
    private String phone;

    @ElementCollection
    private List<String> availableTimes;

    // Getters and Setters
}
```

### Features

* Doctor information stored in MySQL
* Email validation
* Phone number validation
* Available time slots stored as a collection
* Password protected from JSON output

---

# Patient Model

```java
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 5, max = 200)
    private String address;

    @Pattern(regexp = "\\d{10}")
    private String phone;

    // Getters and Setters
}
```

### Features

* Stores patient details
* Validates email format
* Validates phone number format
* Address is required

---

# Appointment Model

```java
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Doctor doctor;

    @ManyToOne
    @NotNull
    private Patient patient;

    @Future
    private LocalDateTime appointmentTime;

    private int status; // 0 = Scheduled, 1 = Completed, 2 = Cancelled

    @Transient
    public LocalDateTime getEndTime() {
        return appointmentTime.plusHours(1);
    }

    // Getters and Setters
}
```

### Features

* Links Doctor and Patient entities
* Appointment date must be in the future
* Calculates appointment end time
* End time is not stored in database

---

# Prescription Model (MongoDB)

```java
@Document(collection = "prescriptions")
public class Prescription {

    @Id
    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    private String patientName;

    @NotNull
    private Long appointmentId;

    @NotNull
    @Size(min = 3, max = 100)
    private String medication;

    @Size(max = 200)
    private String doctorNotes;

    // Getters and Setters
}
```

### Features

* Stored in MongoDB
* Flexible document structure
* Supports prescription-specific information
* Uses validation annotations similar to JPA models

---

# Entity Relationships

## Relational Database (MySQL)

Doctor (1) -------- (*) Appointment (*) -------- (1) Patient

* One Doctor can have many Appointments
* One Patient can have many Appointments
* Each Appointment belongs to one Doctor and one Patient

## MongoDB

Appointment (1) -------- (1) Prescription

* Each Prescription references an Appointment through appointmentId

---

# Validation Summary

The Smart Clinic Management System uses Hibernate Validator annotations to ensure data integrity.

| Annotation                | Purpose                                     |
| ------------------------- | ------------------------------------------- |
| @NotNull                  | Field must not be null                      |
| @Size                     | Enforces minimum and maximum length         |
| @Email                    | Validates email format                      |
| @Pattern                  | Validates phone number format               |
| @Future                   | Ensures appointment dates are in the future |
| @JsonProperty(WRITE_ONLY) | Protects sensitive fields                   |
| @Transient                | Excludes calculated fields from persistence |

---

# Conclusion

These model classes form the foundation of the Smart Clinic Management System. JPA entities are used for structured relational data stored in MySQL, while MongoDB documents provide flexibility for prescription records. Validation annotations ensure data quality, and entity relationships support efficient management of doctors, patients, appointments, and prescriptions.
