package com.example.SpringbootIntern.controllers;

import com.example.SpringbootIntern.models.JwtResponse;
import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.models.UserDetailsDto;
import com.example.SpringbootIntern.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDetailsDto register) {
        return authService.addNewEmployee(register);
    }

    @PostMapping("/login")
    public JwtResponse Login(@RequestBody RegisterDetails login) {
        return authService.authenticateWithToken(login);
    }
}
