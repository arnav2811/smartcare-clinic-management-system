package com.smartcare.cms.controller;

import com.smartcare.cms.dto.LoginDTO;
import com.smartcare.cms.model.Admin;
import com.smartcare.cms.service.AdminService;
import com.smartcare.cms.service.TokenService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AdminService adminService;
    private final TokenService tokenService;

    public AuthController(AdminService adminService, TokenService tokenService) {
        this.adminService = adminService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDTO loginDTO) {
        Admin admin = adminService.findByUsername(loginDTO.getUsername())
            .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!admin.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = tokenService.generateToken(admin);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
