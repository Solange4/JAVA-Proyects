package com.example.demo.controller;

import com.example.demo.dto.NoteCreationDTO;
import com.example.demo.dto.NoteResponseDTO;
import com.example.demo.model.entity.Note;
import com.example.demo.service.NoteService;
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

        NoteResponseDTO noteResponseDTODTO = new NoteResponseDTO(
                note.getTitle(),
                note.getContent(),
                note.getCreated(),
                note.getCategory().getName()
        );
        return new ResponseEntity<>(noteResponseDTODTO, HttpStatus.OK);
    }

    @GetMapping("/notes/category/{id}")
    public ResponseEntity<Page<NoteResponseDTO>> getNotesByCategory(@PathVariable Long id, @PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<NoteResponseDTO> noteList = noteService.getNoteByCategory(id, pageable);
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }

    @PostMapping("/note")
    public ResponseEntity<NoteResponseDTO> createNote(@RequestBody NoteCreationDTO noteCreationDTO){
        Note note = noteService.createNote(noteCreationDTO);

        NoteResponseDTO noteResponseDTO = new NoteResponseDTO(
                note.getTitle(),
                note.getContent(),
                note.getCreated(),
                note.getCategory().getName()
        );
        return new ResponseEntity<>(noteResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/note/{id}")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable Long id, @RequestBody NoteCreationDTO noteCreationDTO){
        Note note = noteService.updateNote(id, noteCreationDTO);

        NoteResponseDTO noteResponseDTO = new NoteResponseDTO(
                note.getTitle(),
                note.getContent(),
                note.getCreated(),
                note.getCategory().getName()
        );
        return new ResponseEntity<>(noteResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}