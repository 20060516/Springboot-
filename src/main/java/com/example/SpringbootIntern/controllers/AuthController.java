package com.example.SpringbootIntern.controllers;

import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.services.AuthService;
import com.example.SpringbootIntern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;

    // Register a new employee
    @PostMapping("/register")
    public String addNewEmployee(@RequestBody RegisterDetails register) {
        return authService.addNewEmployee(register); // use object, not class name
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestBody RegisterDetails login) {
        RegisterDetails user = registerDetailsRepository.findByEmail(login.getEmail());

        if (user != null && user.getPassword().equals(login.getPassword())) {
            return "Employee Logged in Successfully";
        } else {
            return "Invalid Email or Password";
        }
    }
}
