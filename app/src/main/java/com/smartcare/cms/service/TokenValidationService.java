package com.smartcare.cms.service;

import org.springframework.stereotype.Service;

@Service
public class TokenValidationService {

public boolean validateToken(String token) {
    return token != null && !token.isBlank();
}

public boolean validateRole(String token, String role) {

    if ("admin-token".equals(token) && "ADMIN".equalsIgnoreCase(role)) {
        return true;
    }

    if ("doctor-token".equals(token) && "DOCTOR".equalsIgnoreCase(role)) {
        return true;
    }

    return false;
}

}
