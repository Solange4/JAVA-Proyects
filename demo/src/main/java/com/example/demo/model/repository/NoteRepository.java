package com.example.demo.model.repository;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    // Listar notas por categoria
    List<Note> findByCategory(Category category);
}
