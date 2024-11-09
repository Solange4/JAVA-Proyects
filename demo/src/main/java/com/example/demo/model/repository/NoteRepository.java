package com.example.demo.model.repository;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    // Listar notas por categoria
    @Query("SELECT n FROM Note n WHERE n.category.id = :categoryId")
    List<Note> findByCategory(Category category); // tarea: aprender a nombrar los metodos :reglas.
}
