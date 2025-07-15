package com.example.SpringbootIntern.controllers;

import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.models.UserDetailsDto;
import com.example.SpringbootIntern.services.AuthService;
import com.example.SpringbootIntern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterDetails loginRequest) {
        Map<String, Object> response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }
}
