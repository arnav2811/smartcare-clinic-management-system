# Smart Clinic Management System - Database Design

## Overview

The Smart Clinic Management System uses a hybrid database architecture that combines MySQL and MongoDB.

* MySQL is used for structured and relational data such as patients, doctors, appointments, and administrators.
* MongoDB is used for flexible document-based data such as prescriptions that may contain varying numbers of medicines, notes, and treatment instructions.

This approach leverages the strengths of both relational and NoSQL databases.

---

# MySQL Database Design

## Table: admin

| Column Name | Data Type    | Constraints                 |
| ----------- | ------------ | --------------------------- |
| admin_id    | BIGINT       | PRIMARY KEY, AUTO_INCREMENT |
| username    | VARCHAR(50)  | NOT NULL, UNIQUE            |
| password    | VARCHAR(255) | NOT NULL                    |
| email       | VARCHAR(100) | NOT NULL, UNIQUE            |
| created_at  | TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   |

### Primary Key

* admin_id

---

## Table: doctors

| Column Name      | Data Type    | Constraints                 |
| ---------------- | ------------ | --------------------------- |
| doctor_id        | BIGINT       | PRIMARY KEY, AUTO_INCREMENT |
| first_name       | VARCHAR(50)  | NOT NULL                    |
| last_name        | VARCHAR(50)  | NOT NULL                    |
| email            | VARCHAR(100) | NOT NULL, UNIQUE            |
| specialization   | VARCHAR(100) | NOT NULL                    |
| phone_number     | VARCHAR(20)  | NOT NULL                    |
| experience_years | INT          | NOT NULL                    |

### Primary Key

* doctor_id

---

## Table: patients

| Column Name   | Data Type    | Constraints                 |
| ------------- | ------------ | --------------------------- |
| patient_id    | BIGINT       | PRIMARY KEY, AUTO_INCREMENT |
| first_name    | VARCHAR(50)  | NOT NULL                    |
| last_name     | VARCHAR(50)  | NOT NULL                    |
| email         | VARCHAR(100) | NOT NULL, UNIQUE            |
| phone_number  | VARCHAR(20)  | NOT NULL                    |
| date_of_birth | DATE         | NOT NULL                    |
| gender        | VARCHAR(10)  | NOT NULL                    |

### Primary Key

* patient_id

---

## Table: appointments

| Column Name      | Data Type    | Constraints                 |
| ---------------- | ------------ | --------------------------- |
| appointment_id   | BIGINT       | PRIMARY KEY, AUTO_INCREMENT |
| patient_id       | BIGINT       | NOT NULL                    |
| doctor_id        | BIGINT       | NOT NULL                    |
| appointment_date | DATE         | NOT NULL                    |
| appointment_time | TIME         | NOT NULL                    |
| status           | VARCHAR(20)  | NOT NULL                    |
| reason           | VARCHAR(255) | NOT NULL                    |

### Primary Key

* appointment_id

### Foreign Keys

* patient_id REFERENCES patients(patient_id)
* doctor_id REFERENCES doctors(doctor_id)

### Constraints

* NOT NULL on all mandatory fields
* Foreign key integrity enforced between patients, doctors, and appointments

---

# Relationship Summary

* One Doctor can have many Appointments.
* One Patient can have many Appointments.
* Each Appointment belongs to one Doctor and one Patient.
* Admin users manage the overall system.

---

# MongoDB Collection Design

## Collection: prescriptions

MongoDB is used because prescriptions may contain a varying number of medicines and flexible treatment instructions.

### Example Document

```json
{
  "_id": "PR001",
  "appointmentId": 1001,
  "patientId": 501,
  "doctorId": 201,
  "prescriptionDate": "2026-06-18",
  "medications": [
    {
      "medicineName": "Amoxicillin",
      "dosage": "500mg",
      "frequency": "Twice Daily",
      "durationDays": 7
    },
    {
      "medicineName": "Paracetamol",
      "dosage": "650mg",
      "frequency": "Three Times Daily",
      "durationDays": 5
    }
  ],
  "doctorNotes": "Take medicines after meals and drink plenty of water.",
  "followUpRequired": true,
  "followUpDate": "2026-06-25"
}
```

### Design Justification

* The medications field is stored as an array because prescriptions may contain multiple medicines.
* Doctor notes are stored as flexible text.
* MongoDB supports schema flexibility, making it ideal for prescription records that may vary between patients and treatments.
* Nested documents improve readability and reduce the need for multiple relational tables.

---

# Database Design Conclusion

The Smart Clinic Management System uses MySQL for structured clinic operations and MongoDB for flexible prescription management. This hybrid design improves scalability, maintainability, and performance while supporting both relational and document-oriented data requirements.
