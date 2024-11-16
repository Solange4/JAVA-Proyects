package com.example.demo.model.repository;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    // Listar notas por categoria
    Page<Note> findByCategory(Category category, Pageable pageable);
    // Listar notas en paginación
    Page<Note> findAll(Pageable pageable);
    // Listar notas por título
    Optional<Note> findByTitle(String title); // Optional porque no estamos seguros si hay registros
    // Listor notas por usuario
    Page<Note> findByUserId(Long userId, Pageable pageable);
}
