package com.example.SpringbootIntern.repository;

import com.example.SpringbootIntern.models.Task;
import com.example.SpringbootIntern.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByEmployee(RegisterDetails employee);
}
