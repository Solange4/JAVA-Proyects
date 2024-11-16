package com.example.demo.service;

import com.example.demo.dto.NoteCreationDTO;
import com.example.demo.dto.NoteResponseDTO;
import com.example.demo.exception.CategoryException;
import com.example.demo.exception.NoteException;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.CategoryRepository;
import com.example.demo.model.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public NoteService(NoteRepository noteRepository, CategoryRepository categoryRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    private NoteResponseDTO toDto(Note note){
        return new NoteResponseDTO(
                note.getTitle(),
                note.getContent(),
                note.getCreated(),
                note.getCategory().getName(),
                note.getUser().getUsername()
        );
    }

    private void validateNoteNotExists(String title, Long userId){
        noteRepository.findByTitle(title).ifPresent(note -> {
            if (note.getUser().getId().equals(userId)) {
                throw new NoteException("Note already exists with title: " + title + " for this user");
            }
        });
    }

    public Note createNote(NoteCreationDTO noteCreationDTO, Long userId) {
        validateNoteNotExists(noteCreationDTO.title(), userId);

        Category category = categoryRepository.findById(noteCreationDTO.categoryId()).orElseThrow();
        User user = userService.getUserById(userId);

        if (!category.getUser().getId().equals(userId)) {
            throw new CategoryException("Category does not belong to user");
        }

        Note note = new Note();
        note.setTitle(noteCreationDTO.title());
        note.setContent(noteCreationDTO.content());
        note.setCreated(LocalDateTime.now());
        note.setCategory(category);
        note.setUser(user);

        return noteRepository.save(note);
    }

    public Note updateNote(Long id, NoteCreationDTO noteCreationDTO, Long userId){
        Note note = getNoteById(id);
        if (!note.getUser().getId().equals(userId)) {
            throw new NoteException("Note does not belong to user");
        }

        Category category = categoryRepository.findById(noteCreationDTO.categoryId()).orElseThrow();
        if (!category.getUser().getId().equals(userId)) {
            throw new CategoryException("Category does not belong to user");
        }

        note.setTitle(noteCreationDTO.title());
        note.setContent(noteCreationDTO.content());
        note.setCategory(category);

        return noteRepository.save(note);
    }

    public void deleteNote(Long id, Long userId){
        Note note = getNoteById(id);
        if (!note.getUser().getId().equals(userId)) {
            throw new NoteException("Note does not belong to user");
        }

        noteRepository.delete(note);
    }

    public Page<NoteResponseDTO> getAllNotes(Pageable pageable){
        Page<Note> notes = noteRepository.findAll(pageable);
        return notes.map(this::toDto);
    }

    public Note getNoteById(Long id){
        return noteRepository.findById(id).orElseThrow(() -> new NoteException("Note not found with id: " + id));
    }

    public Page<NoteResponseDTO> getNoteByCategory(Long categoryId, Long userId, Pageable pageable){
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if(!category.getUser().getId().equals(userId)){
            throw new CategoryException("Category does not belong to user");
        }
        Page<Note> notes = noteRepository.findByCategory(category, pageable);
        return notes.map(this::toDto);
    }

    public Page<NoteResponseDTO> getNotesByUser(Long userId, Pageable pageable){
        userService.getUserById(userId);

        Page<Note> notes = noteRepository.findByUserId(userId, pageable);
        return notes.map(this::toDto);
    }
}
