package com.example.springboot.Services;

import com.example.springboot.models.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HelloWorldService {

    List<Employee> Emp = new ArrayList<>(
        Arrays.asList(new Employee(1,"Pavi","Trainer"),
        new Employee(2,"Pranavi","Manager"))
        );

    public List<Employee> helloWorld(){
        return Emp;
    }

    public String get(){
        return "This is get method";
    }

    public String post(){
        return "This is post method";
    }

    public String put(){
        return "This is put method";
    }

    public String delete(){
        return "This is delete method";
    }
}
