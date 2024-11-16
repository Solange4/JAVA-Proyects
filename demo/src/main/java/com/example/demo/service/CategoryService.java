package com.example.demo.service;

import com.example.demo.exception.CategoryException;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    public CategoryService(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    public Category createCategory(Category category, Long userId){
        User user = userService.getUserById(userId);
        if (categoryRepository.existsByNameAndUserId(category.getName(), userId)) {
            throw new CategoryException("Category already exists for this user");
        }

        category.setUser(user);

        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id, Long userId){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryException("Category not found"));
        if (!category.getUser().getId().equals(userId)) {
            throw new CategoryException("Category not found for this user");
        }
        return category;
    }

    public List<Category> getCategoriesByUser(Long userId) {
        userService.getUserById(userId);
        return categoryRepository.findByUserId(userId);
    }


    public Category updateCategory(Long id, Category category, Long userId){
        Category categoryToUpdate = getCategoryById(id, userId);
        if (!categoryToUpdate.getName().equals(category.getName()) &&
                categoryRepository.existsByNameAndUserId(category.getName(), userId)) {
            throw new CategoryException("Category already exists for this user");
        }

        categoryToUpdate.setName(category.getName());

        return categoryRepository.save(categoryToUpdate);
    }

    public void deleteCategory(Long id, Long userId){
        Category category = getCategoryById(id, userId);
        categoryRepository.delete(category);
    }
}