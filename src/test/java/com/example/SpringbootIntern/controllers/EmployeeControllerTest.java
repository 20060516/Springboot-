package com.example.SpringbootIntern.controllers;

import com.example.SpringbootIntern.models.RegisterDetails;
import com.example.SpringbootIntern.models.Task;
import com.example.SpringbootIntern.models.UserDetailsDto;
import com.example.SpringbootIntern.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/*
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoute() {
        String result = employeeController.route();
        assertEquals("Welcome to SpringBoot Security", result);
    }

    @Test
    void testGetMethod() {
        RegisterDetails emp1 = new RegisterDetails();
        RegisterDetails emp2 = new RegisterDetails();
        when(employeeService.getMethod()).thenReturn(Arrays.asList(emp1, emp2));

        List<RegisterDetails> result = employeeController.getMethod();
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllEmployees() {
        RegisterDetails emp = new RegisterDetails();
        emp.setName("Pavi.P");
        when(employeeService.getAllEmployees()).thenReturn(List.of(emp));
        List<RegisterDetails> employees = employeeController.getAllEmployees();
        assertEquals(1, employees.size());
        assertEquals("Pavi.P", employees.get(0).getName());
    }

    @Test
    void testGetEmployeeById() {
        RegisterDetails emp = new RegisterDetails();
        emp.setEmpId(106);
        emp.setName("Pavi");
        when(employeeService.getEmployeeById(100)).thenReturn(emp);
        RegisterDetails result = employeeController.getEmployeeById(100);
        assertEquals("Pavi", result.getName());
        assertEquals(106, result.getEmpId());
    }

    @Test
    void testAddEmployee() {
        UserDetailsDto dto = new UserDetailsDto();
        dto.setName("Employee");
        when(employeeService.addNewEmployee(dto)).thenReturn("Added");
        String response = employeeController.addEmployee(dto);
        assertEquals("Added", response);
    }

}*/

class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoute(){
        String result = employeeController.route();
        assertEquals("Welcome to SpringBoot Security",result);
    }

    @Test
    void testGetMethod(){
        RegisterDetails emp1 = new RegisterDetails();
        RegisterDetails emp2 = new RegisterDetails();
        when(employeeService.getMethod()).thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result = employeeController.getMethod();
        when(employeeService.getMethod()).
                thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result1 = employeeController.getMethod();
        assertEquals(2,result.size());
    }
}
