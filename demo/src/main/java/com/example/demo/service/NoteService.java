package com.example.demo.service;

import com.example.demo.dto.NoteDTO;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;
import com.example.demo.model.repository.CategoryRepository;
import com.example.demo.model.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    public final CategoryRepository categoryRepository;

    public NoteService(NoteRepository noteRepository, CategoryRepository categoryRepository) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
    }

    public NoteDTO toDto(Note note){
        return new NoteDTO(
                note.getTitle(),
                note.getContent(),
                note.getCategory().getId()
        );
    }

    public Note createNote(NoteDTO noteDTO) {
        Note note = new Note();
        note.setTitle(noteDTO.title());
        note.setContent(noteDTO.content());
        Category category = categoryRepository.findById(noteDTO.categoryId()).orElse(null);
        note.setCategory(category);
        noteRepository.save(note);
        return note;
    }

    public Note updateNote(Long id, NoteDTO noteDTO){
        Note note = getNoteById(id);
        note.setTitle(noteDTO.title());
        note.setContent(noteDTO.content());
        Category category = categoryRepository.findById(noteDTO.categoryId()).orElse(null);
        note.setCategory(category);
        noteRepository.save(note);
        return note;
    }

    public void deleteNote(Long id){
        Note note = getNoteById(id);
        noteRepository.delete(note);
    }

    public Page<NoteDTO> getAllNotes(Pageable pageable){
        Page<Note> notes = noteRepository.findAll(pageable);
        return notes.map(this::toDto);
    }

    public Note getNoteById(Long id){
        return noteRepository.findById(id).orElse(null);
    }

    public List<NoteDTO> getNoteByCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElse(null);
        List<Note> notes = noteRepository.findByCategory(category);
        return notes.stream().map(this::toDto).toList();
    }

}
