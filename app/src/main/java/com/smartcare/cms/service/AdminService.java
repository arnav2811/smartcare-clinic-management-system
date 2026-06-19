package com.smartcare.cms.service;

import com.smartcare.cms.model.Admin;
import com.smartcare.cms.repository.AdminRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findAll().stream()
            .filter(admin -> admin.getUsername().equals(username))
            .findFirst();
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
}
