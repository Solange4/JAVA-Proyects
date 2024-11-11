package com.example.demo.controller;

import com.example.demo.dto.NoteDTO;
import com.example.demo.model.entity.Note;
import com.example.demo.service.NoteService;
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
public class NoteController {

    private NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<Page<NoteDTO>> getAllNotes(@PageableDefault(page = 0, size = 10) Pageable pageable){
        Page<NoteDTO> noteList = noteService.getAllNotes(pageable);
        return new ResponseEntity<>(noteList, HttpStatus.OK);
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id){
        Note note = noteService.getNoteById(id);
        NoteDTO noteDTO = new NoteDTO(
                note.getTitle(),
                note.getContent(),
                note.getCategory().getId()
        );
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    @GetMapping("/notes/category/{id}")
    public ResponseEntity<List<NoteDTO>> getNotesByCategory(@PathVariable Long id){
        List<NoteDTO> noteDTO = noteService.getNoteByCategory(id);
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    @PostMapping("/note")
    public ResponseEntity<NoteDTO> create(@RequestBody NoteDTO noteDTO){
        Note note = noteService.createNote(noteDTO);
        NoteDTO creatednoteDTO = new NoteDTO(
                note.getTitle(),
                note.getContent(),
                note.getCategory().getId()
        );
        return new ResponseEntity<>(creatednoteDTO, HttpStatus.CREATED);
    }

    @PutMapping("/note/{id}")
    public ResponseEntity<NoteDTO> update(@PathVariable Long id, @RequestBody NoteDTO noteDTO){
        Note note = noteService.updateNote(id, noteDTO);
        NoteDTO updatedNoteDTO = new NoteDTO(
                note.getTitle(),
                note.getContent(),
                note.getCategory().getId()
        );
        return new ResponseEntity<>(updatedNoteDTO, HttpStatus.OK);
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}