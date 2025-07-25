package com.example.SpringbootIntern.repository;

import com.example.SpringbootIntern.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByJob(String job);
}