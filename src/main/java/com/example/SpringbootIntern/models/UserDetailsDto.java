package com.example.SpringbootIntern.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDetailsDto {

    private Long empId;
    private String name;
    private String email;
    private String userName;
    private String password;
    private Set<String> roleName;
}
