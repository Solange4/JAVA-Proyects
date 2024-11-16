package com.example.demo.service;

import com.example.demo.exception.CategoryException;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.xml.catalog.CatalogException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public CategoryService(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    private void validateCategoryNotExists(String name){
        categoryRepository.findByName(name).ifPresent(category -> {
            throw new CatalogException("Category already exists with name: " + name);
        });
    }

    public Category createCategory(Category category, Long userId){
        User user = userService.getUserById(userId);
        if (categoryRepository.existsByNameAndUserId(category.getName(), userId)) {
            throw new CategoryException("Category already exists for this user");
        }

        return categoryRepository.save(category);
    }

    private Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new CatalogException("Category not with ID: " + id));
    }

    public Category updateCategory(Long id, Category category){
        Category categoryToUpdate = getCategoryById(id);
        categoryToUpdate.setName(category.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    public Iterable<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}