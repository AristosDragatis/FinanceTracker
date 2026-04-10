package com.example.financetracker.repository;

import com.example.financetracker.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // fetch categories based on their type
    List<Category> findByType(String type);
}
