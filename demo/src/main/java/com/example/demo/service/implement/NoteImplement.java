package com.example.demo.service.implement;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;
import com.example.demo.model.repository.NoteRepository;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteImplement implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void delete(Note note) {
        noteRepository.delete(note);
    }

    @Override
    public Iterable<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> findByCategory(Category category) {
        return noteRepository.findByCategory(category);
    }

    @Override
    public Note findById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }
}
