package com.smartcare.cms.service;

import com.smartcare.cms.exception.ResourceNotFoundException;
import com.smartcare.cms.model.Patient;
import com.smartcare.cms.repository.PatientRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
    }

    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient update(Long id, Patient patient) {
        Patient existing = findById(id);
        existing.setName(patient.getName());
        existing.setEmail(patient.getEmail());
        existing.setAddress(patient.getAddress());
        existing.setPhone(patient.getPhone());
        return patientRepository.save(existing);
    }

    public void delete(Long id) {
        patientRepository.delete(findById(id));
    }
}
