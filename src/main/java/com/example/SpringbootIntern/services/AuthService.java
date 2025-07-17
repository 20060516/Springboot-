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
    private RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String addNewEmployee(UserDetailsDto register) {
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

    public String authenticate(RegisterDetails login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUserName(), login.getPassword()));
        return jwtTokenProvider.generateToken(authentication);
    }

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
