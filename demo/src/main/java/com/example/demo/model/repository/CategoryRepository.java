package com.example.demo.model.repository;

import com.example.demo.model.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByUserId(Long userId);
    boolean existsByNameAndUserId(String name, Long userId);
}
