package com.example.financetracker.service;

import com.example.financetracker.domain.Category;
import com.example.financetracker.repository.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryMapper categoryMapper;

    // constructor injection
    public CategoryService(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }

    // get all the categories (e.g. Dashboard)
    public List<Category> getAllCategories(){
        return categoryMapper.findAll();
    }


    // get categories by type (e.g. INCOME)
    public List<Category> getCategoriesByType(String type){
        return categoryMapper.findByType(type.toUpperCase());
    }
}
