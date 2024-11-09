package com.example.demo.controller;

import com.example.demo.dto.NoteDTO;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;
import com.example.demo.service.note.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NoteController {

  private final NoteService noteService;

  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }


  @PostMapping("note")

  public Note create(@RequestBody Note note) { // Usar DTOS para requests y responses
    return noteService.save(note); // Usar ResponseEntity para retornar el status
  }

  @PutMapping("note")
  @ResponseStatus(HttpStatus.CREATED)
  public Note update(@RequestBody Note note) {
    return noteService.save(note);
  }

  @DeleteMapping("note/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    Note noteDelete = noteService.findById(id);
    noteService.delete(noteDelete);
  }

  @GetMapping("note/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Note showById(@PathVariable Long id) {
    return noteService.findById(id);
  }

  @GetMapping("notes")
  @ResponseStatus(HttpStatus.OK)
  public Iterable<NoteDTO> showAll() { // Usar paginacion: Pageable
    return noteService.findAll();
  }

  @GetMapping("notes/category/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<NoteDTO> showByCategory(@PathVariable Long id) {
    Category category = new Category();
    category.setId(id);
    List<NoteDTO> notesByCategory = noteService.findByCategory(category);
    return notesByCategory;
  }
}
