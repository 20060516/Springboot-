package com.example.SpringbootIntern.services;

import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RegisterDetailsRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegisterDetails user = userRepo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new User(user.getUserName(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(RegisterDetails user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(role -> role.getRoleName()) // e.g., "ROLE_USER", "ROLE_ADMIN"
                .collect(Collectors.toSet());

        return roleNames.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
