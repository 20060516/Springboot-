package com.example.SpringbootIntern.services;

import com.example.SpringbootIntern.jwt.JwtTokenProvider;
import com.example.SpringbootIntern.models.*;
import com.example.SpringbootIntern.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());

        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoleName()) {
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }
        registerDetails.setRoles(roles);

        System.out.println("Registration: " + registerDetails);
        registerDetailsRepository.save(registerDetails);
        return "Employee Added Successfully";
    }

    public String authenticate(RegisterDetails login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUserName(), login.getPassword()));
        return jwtTokenProvider.generateToken(authentication);
    }

    // ✅ Fixed method signature and structure
    public JwtResponse authenticateWithToken(RegisterDetails login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword())
        );
        String token = jwtTokenProvider.generateToken(authentication);
        String username = login.getUserName();

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String joinedRoles = String.join(",", roles);

        return new JwtResponse(token, username, joinedRoles);
    }

    public Optional<RegisterDetails> getUserByUsername(String username) {
        return registerRepository.findByUserName(username);
    }
}
