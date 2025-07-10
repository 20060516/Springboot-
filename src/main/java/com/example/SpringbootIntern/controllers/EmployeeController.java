package com.example.SpringbootIntern.controllers;


import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.models.UserDetailsDto;
import com.example.SpringbootIntern.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String route(){
        return "Welcome to SpringBoot Security";
    }


    @GetMapping("/employee")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<RegisterDetails> getMethod(){
        return employeeService.getMethod();
    }


    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public RegisterDetails getEmployeeById(@PathVariable int empId){
        System.out.println();
        return employeeService.getEmployeeById(empId);
    }



//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @GetMapping("/employee/job/{job}")
//    public List<RegisterDetails> getEmployeeByJob(@PathVariable String job){
//        return employeeService.getEmployeeByJob(job);
//    }

    @PostMapping("/employee")
    @PreAuthorize("hasRole('ADMIN')")
    public String postMethod(@RequestBody RegisterDetails employee){
//        Employee employee = new Employee(5,"Sivagami", "Business");
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/employee/{empId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String putMethod(@PathVariable int empId){
        return employeeService.updateEmployee(empId);
    }

    @DeleteMapping("/employee/{empID}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMethod(@PathVariable int empID){
        return employeeService.deleteEmployeeById(empID);
    }
}