package com.example.SpringbootIntern.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class RegisterDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    private String name;
    private String email;
    private String userName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();
}
