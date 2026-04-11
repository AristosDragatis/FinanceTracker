package com.example.financetracker.service;

import com.example.financetracker.domain.Category;
import com.example.financetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // constructor injection
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    // get all the categories (e.g. Dashboard)
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }


    // get categories by type (e.g. INCOME)
    public List<Category> getCategoriesByType(String type){
        return categoryRepository.findByType(type.toUpperCase());
    }
}
