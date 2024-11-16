package com.example.demo.dto;

import java.time.LocalDateTime;

public record NoteResponseDTO (
        String title,
        String content,
        LocalDateTime created,
        String categoryName,
        String username
){}