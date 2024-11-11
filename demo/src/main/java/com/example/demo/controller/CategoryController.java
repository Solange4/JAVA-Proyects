package com.example.demo.controller;

import com.example.demo.model.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<Page<Category>> getAllCategories(@PageableDefault (page = 0, size = 10) Pageable pageable){
        Page<Category> categoryList = categoryService.getAllCategories(pageable);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category newCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(id, category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
}
