package com.example.SpringbootIntern.services;

import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.models.Roles;
import com.example.SpringbootIntern.models.UserDetailsDto;
import com.example.SpringbootIntern.repository.RegisterDetailsRepository;
import com.example.SpringbootIntern.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new employee
    public String addNewEmployee(UserDetailsDto register) {
        if (registerDetailsRepository.existsByEmail(register.getEmail())) {
            return "Email already exists!";
        }

        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setUserName(register.getUserName());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));

        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoleName()) {
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }

        registerDetails.setRoles(roles);
        registerDetailsRepository.save(registerDetails);

        return "Employee Added Successfully";
    }

    // Authenticate user credentials
    public String authenticate(RegisterDetails login) {
        RegisterDetails user = registerDetailsRepository.findByEmail(login.getEmail());
        if (user != null && passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            return "Login Successful";
        }
        return "Login Not Successful";
    }
}
