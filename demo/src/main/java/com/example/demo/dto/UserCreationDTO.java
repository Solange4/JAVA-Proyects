package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreationDTO(
        @NotNull(message = "Username is required")
        @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
        String username,

        @NotNull(message = "Email is required")
        @Size(min = 10, max = 100, message = "Email must be between 10 and 100 characters")
        @Email
        String email,

        @NotNull(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
) {}
