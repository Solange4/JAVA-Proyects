package com.example.demo.model.repository;

import com.example.demo.model.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    // Buscar categoría por nombre
    Optional<Category> findByName(String name);
    List<Category> findAllByUserId(Long userId);
    Optional<Category> findByNameandUserId(String name, Long userId);
    boolean existsByNameAndUserId(String name, Long userId);
}
