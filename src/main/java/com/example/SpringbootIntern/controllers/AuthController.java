package com.example.SpringbootIntern.controllers;

import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.models.UserDetailsDto;
import com.example.SpringbootIntern.services.AuthService;
import com.example.SpringbootIntern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDetailsDto register){
        return authService.addNewEmployee(register);
    }

    @PostMapping("/login")
    public String Login(@RequestBody RegisterDetails login){
        return authService.authenticate(login);
    }
}
