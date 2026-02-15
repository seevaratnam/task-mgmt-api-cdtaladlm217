package com.example.taskapi.repository;

import com.example.taskapi.model.Task;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Task, UUID> {
}