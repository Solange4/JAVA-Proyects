package com.example.demo.service.note;

import com.example.demo.dto.NoteDTO;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;

import java.util.List;

public interface NoteService {
    // Guardar nota - Actualizar nota
    Note save(Note note);
    // Eliminar nota
    void delete(Note note);
    // Listar todas las notas
    Iterable<NoteDTO> findAll();
    // Listar notas por categoria
    List<NoteDTO> findByCategory(Category category);
    // Listar notas por ID
    Note findById(Long id);
}
