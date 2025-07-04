package com.example.springboot.Controllers;

import com.example.springboot.Services.HelloWorldService;
import com.example.springboot.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloworldController {
    @Autowired

    private HelloWorldService hws;

    @GetMapping("/get")
    public List<Employee> getMethod()
    {
        return hws.helloWorld();
    }

    @PostMapping("/post")
    public String postMethod(){
        return hws.post();
    }

    @PutMapping("/put")
    public String putMethod(){
        return hws.put();
    }

    @DeleteMapping("/del")
    public String deleteMethod(){
        return hws.delete();
    }
}
