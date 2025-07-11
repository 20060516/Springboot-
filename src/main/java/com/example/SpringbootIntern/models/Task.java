package com.example.SpringbootIntern.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    private String title;
    private String description;
    private String status = "Pending";

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private RegisterDetails employee;

    public void setEmployee(RegisterDetails employee) {
    }
}
