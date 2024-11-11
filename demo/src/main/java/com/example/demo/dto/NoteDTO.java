package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NoteDTO(
        @NotNull(message = "Title is required")
        @NotBlank(message = "Title is required")
        String title,

        @NotNull(message = "Content is required")
        @NotBlank(message = "Title is required")
        String content,

        @NotNull(message = "Category is required")
        @NotEmpty(message = "Category is required")
        Long categoryId
) {}