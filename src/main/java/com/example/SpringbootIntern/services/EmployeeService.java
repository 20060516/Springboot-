package com.example.SpringbootIntern.services;

import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.models.Task;
import com.example.SpringbootIntern.repository.RegisterDetailsRepository;
import com.example.SpringbootIntern.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<RegisterDetails> getMethod() {
        return registerDetailsRepository.findAll();
    }

    public RegisterDetails getEmployeeById(int empId) {
        return registerDetailsRepository.findById(empId).orElse(new RegisterDetails());
    }

    public String addEmployee(RegisterDetails employee) {
        registerDetailsRepository.save(employee);
        return "Employee Added Successfully";
    }

    public String updateEmployee(int empId) {
        RegisterDetails user = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("No Such User Present"));
        registerDetailsRepository.save(user);
        return "Employee Updated Successfully";
    }

    public String deleteEmployeeById(int empID) {
        registerDetailsRepository.deleteById(empID);
        return "Employee Deleted Successfully";
    }

    public String updateEmployeeById(int empId, RegisterDetails employee) {
        RegisterDetails users = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee ID not found"));

        users.setName(employee.getName());
        users.setEmail(employee.getEmail());
        users.setRoles(employee.getRoles());
        registerDetailsRepository.save(users);
        return "Employee updated successfully By using Id";
    }

    public List<RegisterDetails> getEmployeesByRole(String roleName) {
        return registerDetailsRepository.findAll()
                .stream()
                .filter(emp -> emp.getRoles().stream()
                        .anyMatch(role -> role.getRoleName().equalsIgnoreCase(roleName)))
                .toList();
    }

    public String assignTaskToEmployee(int empId, Task task) {
        RegisterDetails employee = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee ID not found"));
        task.setEmployee(employee);
        taskRepository.save(task);
        return "Task assigned to employee with ID " + empId;
    }

    public List<Task> getTasksByEmployee(int empId) {
        RegisterDetails employee = registerDetailsRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee ID not found"));
        return taskRepository.findByEmployee(employee);
    }
}
