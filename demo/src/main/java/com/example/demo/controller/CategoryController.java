package com.example.demo.controller;

import com.example.demo.model.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping("/user/{userId}/category")
    public ResponseEntity<Category> createCategory(@PathVariable Long userId, @RequestBody Category category){
        Category newCategory = categoryService.createCategory(category, userId);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/categories")
    public ResponseEntity<List<Category>> getUserCategories(@PathVariable Long userId){
        List<Category> categories = categoryService.getCategoriesByUser(userId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long userId,@PathVariable Long id){
        Category category = categoryService.getCategoryById(id, userId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long userId, @PathVariable Long id, @RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(id, category, userId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long userId, @PathVariable Long id){
        categoryService.deleteCategory(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
