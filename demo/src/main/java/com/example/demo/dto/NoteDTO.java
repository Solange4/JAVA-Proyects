package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public record NoteDTO(
    @NotNull(message = "El campo 'title' no puede ser nulo")
    String title,
    @NotNull(message = "El campo 'content' no puede ser nulo")
    String content,
    @NotNull(message = "El campo 'categoryName' no puede ser nulo")
    String categoryName
) {
}
