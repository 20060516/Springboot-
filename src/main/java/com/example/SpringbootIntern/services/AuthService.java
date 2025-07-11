package com.example.SpringbootIntern.services;

import com.example.SpringbootIntern.jwt.JwtTokenProvider;
import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.models.Roles;
import com.example.SpringbootIntern.models.UserDetailsDto;
import com.example.SpringbootIntern.repository.RegisterDetailsRepository;
import com.example.SpringbootIntern.repository.RegisterRepository;
import com.example.SpringbootIntern.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());

        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoleName()) {
            Roles role = RolesRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        registerDetailsRepository.save(registerDetails);
        return "Employee Added Successfully";
    }

    public String authenticate(RegisterDetails login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUserName(),
                        login.getPassword()
                )
        );
        return jwtTokenProvider.generateToken(authentication);
    }

    public Optional<RegisterDetails> getUserByUsername(String username) {
        return registerRepository.findByUserName(username);
    }
}
