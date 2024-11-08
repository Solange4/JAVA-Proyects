package com.example.demo.controller;

import com.example.demo.dto.NoteDTO;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("note")
    public Note create(@RequestBody Note note){
        return noteService.save(note);
    }

    @PutMapping("note")
    public Note update(@RequestBody Note note){
        return noteService.save(note);
    }

    @DeleteMapping("note/{id}")
    public void delete(@PathVariable Long id){
        Note noteDelete = noteService.findById(id);
        noteService.delete(noteDelete);
    }

    @GetMapping("note/{id}")
    public Note showById(@PathVariable Long id){
        return noteService.findById(id);
    }

    @GetMapping("notes")
    public Iterable<NoteDTO> showAll(){
        return noteService.findAll();
    }

    @GetMapping("notes/category/{id}")
    public List<NoteDTO> showByCategory(@PathVariable Long id){
        Category category = new Category();
        category.setId(id);
        List<NoteDTO> notesByCategory = noteService.findByCategory(category);
        return notesByCategory;
    }
}
