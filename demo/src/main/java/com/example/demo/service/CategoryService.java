package com.example.demo.service;

import com.example.demo.model.entity.Category;
import com.example.demo.model.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category){
        Category categoryToUpdate = categoryRepository.findById(id).orElse(null);
        categoryToUpdate.setName(category.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    public Page<Category> getAllCategories(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }
}