package com.example.demo.controller;

import com.example.demo.dto.NoteResponseDTO;
import com.example.demo.dto.UserCreationDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.NoteService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private final NoteService noteService;
    private final UserService userService;
    private final CategoryService categoryService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<UserResponseDTO> userList = userService.getAllUsers(pageable);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username){
        User user = userService.getUserByUsername(username);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreationDTO userCreationDTO){
        User user = userService.createUser(userCreationDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserCreationDTO userCreationDTO){
        User user = userService.updateUser(id, userCreationDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("user/{userId}/notes")
    public ResponseEntity<Page<NoteResponseDTO>> getUserNotes(@PathVariable Long userId, @PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<NoteResponseDTO> noteList = noteService.getNotesByUser(userId, pageable);
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }

    @GetMapping("user/{userId}/categories")
    public ResponseEntity<List<Category>> getUserCategories(@PathVariable Long userId){
        List<Category> categoryList = categoryService.getCategoriesByUser(userId);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
