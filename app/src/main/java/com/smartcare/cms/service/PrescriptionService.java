package com.smartcare.cms.service;

import com.smartcare.cms.exception.ResourceNotFoundException;
import com.smartcare.cms.model.Prescription;
import com.smartcare.cms.repository.PrescriptionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    public Prescription findById(String id) {
        return prescriptionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", id));
    }

    public Prescription create(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public Prescription update(String id, Prescription prescription) {
        Prescription existing = findById(id);
        existing.setPatientName(prescription.getPatientName());
        existing.setAppointmentId(prescription.getAppointmentId());
        existing.setMedication(prescription.getMedication());
        existing.setDoctorNotes(prescription.getDoctorNotes());
        return prescriptionRepository.save(existing);
    }

    public void delete(String id) {
        prescriptionRepository.delete(findById(id));
    }
}
