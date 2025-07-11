package com.example.SpringbootIntern.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
    private String  roles;
}
