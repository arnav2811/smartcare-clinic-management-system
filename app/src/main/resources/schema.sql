CREATE DATABASE IF NOT EXISTS cms;

USE cms;

CREATE TABLE IF NOT EXISTS admin (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS doctor (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  specialty VARCHAR(50) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255),
  phone VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS patient (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL,
  address VARCHAR(200) NOT NULL,
  phone VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS appointment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  doctor_id BIGINT NOT NULL,
  patient_id BIGINT NOT NULL,
  appointment_time DATETIME,
  status INT,
  CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id),
  CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);

CREATE TABLE IF NOT EXISTS doctor_available_times (
  doctor_id BIGINT NOT NULL,
  available_time VARCHAR(50),
  CONSTRAINT fk_doctor_available_times_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
