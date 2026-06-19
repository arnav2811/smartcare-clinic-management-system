package com.smartcare.cms.service;

import com.smartcare.cms.exception.ResourceNotFoundException;
import com.smartcare.cms.model.Doctor;
import com.smartcare.cms.repository.DoctorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
    }

    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor update(Long id, Doctor updatedDoctor) {
        Doctor existing = findById(id);
        existing.setName(updatedDoctor.getName());
        existing.setSpecialty(updatedDoctor.getSpecialty());
        existing.setEmail(updatedDoctor.getEmail());
        existing.setPassword(updatedDoctor.getPassword());
        existing.setPhone(updatedDoctor.getPhone());
        existing.setAvailableTimes(updatedDoctor.getAvailableTimes());
        return doctorRepository.save(existing);
    }

    public void delete(Long id) {
        doctorRepository.delete(findById(id));
    }
}
