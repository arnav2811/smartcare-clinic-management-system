package com.smartcare.cms.service;

import com.smartcare.cms.dto.AppointmentDTO;
import com.smartcare.cms.exception.ResourceNotFoundException;
import com.smartcare.cms.model.Appointment;
import com.smartcare.cms.model.Doctor;
import com.smartcare.cms.model.Patient;
import com.smartcare.cms.repository.AppointmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorService doctorService,
                              PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
    }

    public Appointment create(AppointmentDTO dto) {
        Doctor doctor = doctorService.findById(dto.getDoctorId());
        Patient patient = patientService.findById(dto.getPatientId());
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus());
        return appointmentRepository.save(appointment);
    }

    public Appointment update(Long id, AppointmentDTO dto) {
        Appointment existing = findById(id);
        existing.setDoctor(doctorService.findById(dto.getDoctorId()));
        existing.setPatient(patientService.findById(dto.getPatientId()));
        existing.setAppointmentTime(dto.getAppointmentTime());
        existing.setStatus(dto.getStatus());
        return appointmentRepository.save(existing);
    }

    public void delete(Long id) {
        appointmentRepository.delete(findById(id));
    }
}
