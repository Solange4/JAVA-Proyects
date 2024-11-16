package com.example.demo.controller;

import com.example.demo.dto.NoteCreationDTO;
import com.example.demo.dto.NoteResponseDTO;
import com.example.demo.model.entity.Note;
import com.example.demo.service.NoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<Page<NoteResponseDTO>> getAllNotes(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<NoteResponseDTO> noteList = noteService.getAllNotes(pageable);
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<NoteResponseDTO> getNoteById(@PathVariable Long id){
        Note note = noteService.getNoteById(id);

        NoteResponseDTO noteResponseDTO = new NoteResponseDTO(
                note.getTitle(),
                note.getContent(),
                note.getCreated(),
                note.getCategory().getName(),
                note.getUser().getUsername()
        );
        return new ResponseEntity<>(noteResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/notes/category/{categoryId}")
    public ResponseEntity<Page<NoteResponseDTO>> getNotesByCategory(@PathVariable Long userId, @PathVariable Long categoryId, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<NoteResponseDTO> noteList = noteService.getNoteByCategory(categoryId, userId, pageable);
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/note")
    public ResponseEntity<NoteResponseDTO> createNote(@PathVariable Long userId, @Valid @RequestBody NoteCreationDTO noteCreationDTO) {
        Note note = noteService.createNote(noteCreationDTO, userId);
        NoteResponseDTO noteResponseDTO = new NoteResponseDTO(
                note.getTitle(),
                note.getContent(),
                note.getCreated(),
                note.getCategory().getName(),
                note.getUser().getUsername()
        );
        return new ResponseEntity<>(noteResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}/note/{id}")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody NoteCreationDTO noteCreationDTO) {
        Note note = noteService.updateNote(id, noteCreationDTO, userId);
        NoteResponseDTO noteResponseDTO = new NoteResponseDTO(
                note.getTitle(),
                note.getContent(),
                note.getCreated(),
                note.getCategory().getName(),
                note.getUser().getUsername()
        );
        return new ResponseEntity<>(noteResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/note/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long userId, @PathVariable Long id) {
        noteService.deleteNote(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}