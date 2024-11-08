package com.example.demo.service.implement;

import com.example.demo.dto.NoteDTO;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;
import com.example.demo.model.repository.NoteRepository;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteImplement implements NoteService {
    @Autowired // Inyeccion de dependencia
    private NoteRepository noteRepository;

    public NoteDTO toDto(Note note){
        return new NoteDTO(
                note.getTitle(),
                note.getContent(),
                note.getCategory().getName()
        );
    }

    @Transactional //Maneja excepciones por defecto
    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Transactional
    @Override
    public void delete(Note note) {
        noteRepository.delete(note);
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoteDTO> findAll() {
        List<Note> notes = (List<Note>) noteRepository.findAll();
        return notes.stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoteDTO> findByCategory(Category category) {
        List<Note> notes = noteRepository.findByCategory(category);
        return notes.stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Note findById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }
}
