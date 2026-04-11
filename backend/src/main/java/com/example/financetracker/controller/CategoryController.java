package com.example.financetracker.controller;

import com.example.financetracker.domain.Category;
import com.example.financetracker.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(@RequestParam(required = false) String type){

        // if the user did not specify the type of the category
        if(type == null || type.isBlank()){
            List<Category> allCategories = categoryService.getAllCategories();
            return ResponseEntity.ok(allCategories);
        }

        // if the user specifies the type of the category
        List<Category> filteredCategories = categoryService.getCategoriesByType(type);
        return ResponseEntity.ok(filteredCategories);
    }

}
