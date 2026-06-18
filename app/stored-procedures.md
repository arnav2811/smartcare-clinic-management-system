# Stored Procedures for Smart Clinic Management System

## Sample Data

### Doctors

```sql
INSERT INTO doctor (id, name, specialty, email, phone)
VALUES
(1, 'Dr. Sarah Wilson', 'Cardiology', 'sarah@clinic.com', '9876543210'),
(2, 'Dr. James Brown', 'Dermatology', 'james@clinic.com', '9876543211'),
(3, 'Dr. Emily Davis', 'General Medicine', 'emily@clinic.com', '9876543212');
```

### Patients

```sql
INSERT INTO patient (id, name, email, address, phone)
VALUES
(1, 'John Smith', 'john@example.com', 'New York', '9123456789'),
(2, 'Mary Johnson', 'mary@example.com', 'Chicago', '9234567890'),
(3, 'David Lee', 'david@example.com', 'Boston', '9345678901'),
(4, 'Sophia Clark', 'sophia@example.com', 'Seattle', '9456789012');
```

### Appointments

```sql
INSERT INTO appointment
(id, doctor_id, patient_id, appointment_time, status)
VALUES
(1, 1, 1, '2026-06-10 09:00:00', 1),
(2, 1, 2, '2026-06-10 10:00:00', 1),
(3, 2, 3, '2026-06-10 11:00:00', 1),
(4, 3, 4, '2026-06-15 14:00:00', 1),
(5, 1, 3, '2026-07-05 09:30:00', 1),
(6, 1, 4, '2026-07-08 15:00:00', 1),
(7, 2, 1, '2026-07-12 10:30:00', 1),
(8, 1, 2, '2025-05-20 09:00:00', 1),
(9, 1, 3, '2025-08-18 11:00:00', 1),
(10, 3, 4, '2025-09-01 14:00:00', 1);
```

---

## Stored Procedure 1: Daily Appointment Report by Doctor

```sql
DELIMITER $$

CREATE PROCEDURE GetDailyAppointmentReportByDoctor(
    IN reportDate DATE
)
BEGIN
    SELECT
        d.id AS doctor_id,
        d.name AS doctor_name,
        COUNT(a.id) AS total_appointments
    FROM doctor d
    LEFT JOIN appointment a
        ON d.id = a.doctor_id
    WHERE DATE(a.appointment_time) = reportDate
    GROUP BY d.id, d.name
    ORDER BY total_appointments DESC;
END$$

DELIMITER ;
```

---

## Stored Procedure 2: Doctor with Most Patients in a Month

```sql
DELIMITER $$

CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(
    IN reportYear INT,
    IN reportMonth INT
)
BEGIN
    SELECT
        d.id,
        d.name,
        COUNT(DISTINCT a.patient_id) AS patients_seen
    FROM doctor d
    JOIN appointment a
        ON d.id = a.doctor_id
    WHERE YEAR(a.appointment_time) = reportYear
      AND MONTH(a.appointment_time) = reportMonth
    GROUP BY d.id, d.name
    ORDER BY patients_seen DESC
    LIMIT 1;
END$$

DELIMITER ;
```

---

## Stored Procedure 3: Doctor with Most Patients in a Year

```sql
DELIMITER $$

CREATE PROCEDURE GetDoctorWithMostPatientsByYear(
    IN reportYear INT
)
BEGIN
    SELECT
        d.id,
        d.name,
        COUNT(DISTINCT a.patient_id) AS patients_seen
    FROM doctor d
    JOIN appointment a
        ON d.id = a.doctor_id
    WHERE YEAR(a.appointment_time) = reportYear
    GROUP BY d.id, d.name
    ORDER BY patients_seen DESC
    LIMIT 1;
END$$

DELIMITER ;
```

---

## Procedure Execution Statements

### Run Daily Appointment Report

```sql
CALL GetDailyAppointmentReportByDoctor('2026-06-10');
```

### Run Monthly Top Doctor Report

```sql
CALL GetDoctorWithMostPatientsByMonth(2026, 7);
```

### Run Yearly Top Doctor Report

```sql
CALL GetDoctorWithMostPatientsByYear(2026);
```

---

## Deliverables

### GetDailyAppointmentReportByDoctor

```sql
CALL GetDailyAppointmentReportByDoctor('2026-06-10');
```

### GetDoctorWithMostPatientsByMonth

```sql
CALL GetDoctorWithMostPatientsByMonth(2026, 7);
```

### GetDoctorWithMostPatientsByYear

```sql
CALL GetDoctorWithMostPatientsByYear(2026);
```
