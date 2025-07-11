package com.example.SpringbootIntern.services;

import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.models.Roles;
import com.example.SpringbootIntern.models.Task;
import com.example.SpringbootIntern.models.UserDetailsDto;
import com.example.SpringbootIntern.repository.RegisterDetailsRepository;
import com.example.SpringbootIntern.repository.RolesRepository;
import com.example.SpringbootIntern.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<RegisterDetails> getAllEmployees() {
        return registerDetailsRepository.findAll();
    }

    public RegisterDetails getEmployeeById(int empId) {
        return registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee ID not found: " + empId));
    }

    public String addEmployee(RegisterDetails employee) {
        registerDetailsRepository.save(employee);
        return "Employee Added Successfully";
    }

    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setUserName(register.getUserName());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));

        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoleName()) {
            Roles role = RolesRepository.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }

        registerDetails.setRoles(roles);
        registerDetailsRepository.save(registerDetails);
        return "Employee Added Successfully using DTO";
    }

    public String updateEmployeeById(int empId, RegisterDetails employee) {
        RegisterDetails existing = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee ID not found"));

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setUserName(employee.getUserName());
        existing.setPassword(passwordEncoder.encode(employee.getPassword()));
        existing.setRoles(employee.getRoles());

        registerDetailsRepository.save(existing);
        return "Employee updated successfully by ID";
    }

    public String deleteEmployeeById(int empId) {
        registerDetailsRepository.deleteById(empId);
        return "Employee Deleted Successfully";
    }

    public List<RegisterDetails> getEmployeesByRole(String roleName) {
        return registerDetailsRepository.findAll().stream()
                .filter(emp -> emp.getRoles().stream()
                        .anyMatch(role -> role.getRoleName().equalsIgnoreCase(roleName)))
                .collect(Collectors.toList());
    }

    public String assignTaskToEmployee(int empId, Task task) {
        RegisterDetails employee = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee ID not found"));

        task.setEmployee(employee);
        taskRepository.save(task);
        return "Task assigned to employee with ID ";
    }

    public List<Task> getTasksByEmployee(int empId) {
        RegisterDetails employee = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee ID not found"));
        return taskRepository.findByEmployee(employee);
    }
}
