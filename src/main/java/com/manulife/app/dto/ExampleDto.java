package com.example.taskapi.dto;

import com.example.taskapi.model.TaskStatus;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ExampleDto(
    UUID id,
    String title,
    String description,
    TaskStatus status,
    LocalDate dueDate,
    Instant createdAt,
    Instant updatedAt
) {
}