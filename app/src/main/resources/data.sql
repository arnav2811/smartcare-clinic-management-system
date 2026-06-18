INSERT INTO admin (username, password) VALUES
('admin', 'admin123');

INSERT INTO doctor (name, specialty, email, password, phone) VALUES
('Dr. Ayesha Khan', 'Cardiology', 'ayesha.khan@example.com', 'password1', '9876543210'),
('Dr. Rahul Mehta', 'Dermatology', 'rahul.mehta@example.com', 'password2', '9123456780');

INSERT INTO patient (name, email, address, phone) VALUES
('Reena Sharma', 'reena.sharma@example.com', '12 MG Road, Pune', '9988776655'),
('Amit Patel', 'amit.patel@example.com', '45 Park Street, Mumbai', '9870012345');

INSERT INTO appointment (doctor_id, patient_id, appointment_time, status) VALUES
(1, 1, '2026-07-20T14:00:00', 0),
(2, 2, '2026-07-21T10:30:00', 0);
