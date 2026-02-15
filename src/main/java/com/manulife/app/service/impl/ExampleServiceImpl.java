package com.example.taskapi.service;

import com.example.taskapi.dto.TaskCreateRequest;
import com.example.taskapi.dto.TaskResponse;
import com.example.taskapi.dto.TaskUpdateRequest;
import com.example.taskapi.exception.ResourceNotFoundException;
import com.example.taskapi.model.Task;
import com.example.taskapi.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExampleService {

    private final TaskRepository taskRepository;

    public ExampleService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse create(TaskCreateRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setDueDate(request.dueDate());
        Task saved = taskRepository.save(task);
        return toResponse(saved);
    }

    public TaskResponse getById(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        return toResponse(task);
    }

    public List<TaskResponse> getAll() {
        return taskRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public TaskResponse replace(UUID id, TaskUpdateRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setDueDate(request.dueDate());
        Task saved = taskRepository.save(task);
        return toResponse(saved);
    }

    public TaskResponse update(UUID id, TaskUpdateRequest request) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        if (request.title() != null) {
            task.setTitle(request.title());
        }
        if (request.description() != null) {
            task.setDescription(request.description());
        }
        if (request.status() != null) {
            task.setStatus(request.status());
        }
        if (request.dueDate() != null) {
            task.setDueDate(request.dueDate());
        }
        Task saved = taskRepository.save(task);
        return toResponse(saved);
    }

    public void delete(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        taskRepository.delete(task);
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}