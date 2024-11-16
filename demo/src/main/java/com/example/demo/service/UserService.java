package com.example.demo.service;

import com.example.demo.dto.UserCreationDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.exception.UserException;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserResponseDTO toDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    private void validateUsernameNotExists(String username) {
        userRepository.findByUsername(username)
                .ifPresent(existingUser -> {
                    throw new UserException("Username already exists: " + username);
                });
    }

    private void validateEmailNotExists(String email) {
        userRepository.findByEmail(email)
                .ifPresent(existingUser -> {
                    throw new UserException("Email already exists: " + email);
                });
    }

    public User createUser(UserCreationDTO userDTO) {
        validateUsernameNotExists(userDTO.username());
        validateEmailNotExists(userDTO.email());

        User user = new User();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setNotes(new ArrayList<>());

        return userRepository.save(user);
    }

    public User updateUser(Long id, UserCreationDTO userDTO) {
        User user = getUserById(id);

        if (!user.getUsername().equals(userDTO.username())) {
            validateUsernameNotExists(userDTO.username());
            user.setUsername(userDTO.username());
        }

        if (!user.getEmail().equals(userDTO.email())) {
            validateEmailNotExists(userDTO.email());
            user.setEmail(userDTO.email());
        }

        if (userDTO.password() != null && !userDTO.password().isEmpty()) {
            user.setPassword(userDTO.password());
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(this::toDto);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with ID: " + id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("User not found with username: " + username));
    }
}