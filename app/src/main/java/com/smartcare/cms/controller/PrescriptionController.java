package com.smartcare.cms.controller;

import com.smartcare.cms.model.Prescription;
import com.smartcare.cms.service.PrescriptionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.findAll();
    }

    @GetMapping("/{id}")
    public Prescription getPrescription(@PathVariable String id) {
        return prescriptionService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@Valid @RequestBody Prescription prescription) {
        return ResponseEntity.ok(prescriptionService.create(prescription));
    }

    @PutMapping("/{id}")
    public Prescription updatePrescription(@PathVariable String id, @Valid @RequestBody Prescription prescription) {
        return prescriptionService.update(id, prescription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable String id) {
        prescriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
