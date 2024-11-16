package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record NoteCreationDTO(
        @NotBlank(message = "Title is required")
        @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
        String title,

        @NotBlank(message = "Content is required")
        @Size(max = 1000, message = "Content cannot exceed 1000 characters")
        String content,

        @NotNull(message = "Category is required")
        @Positive(message = "Category ID must be positive")
        Long categoryId
) {}