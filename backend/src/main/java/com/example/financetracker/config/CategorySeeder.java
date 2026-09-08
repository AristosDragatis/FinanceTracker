package com.example.financetracker.config;

import com.example.financetracker.domain.Category;
import com.example.financetracker.domain.CategoryType;
import com.example.financetracker.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Seeds the default (global) categories on startup if they are missing.
 * Replaces the old data.sql, which did a destructive TRUNCATE on every boot.
 * Idempotent: only inserts categories whose name does not already exist.
 */
@Component
public class CategorySeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CategorySeeder.class);

    private static final Map<String, CategoryType> DEFAULT_CATEGORIES = Map.ofEntries(
            Map.entry("Salary", CategoryType.INCOME),
            Map.entry("Gift", CategoryType.INCOME),
            Map.entry("Investment", CategoryType.INCOME),
            Map.entry("Transfer", CategoryType.TRANSFER),
            Map.entry("Supermarket", CategoryType.EXPENSE),
            Map.entry("Rent", CategoryType.EXPENSE),
            Map.entry("Utilities", CategoryType.EXPENSE),
            Map.entry("Entertainment", CategoryType.EXPENSE),
            Map.entry("Transport/Gas", CategoryType.EXPENSE),
            Map.entry("Healthcare", CategoryType.EXPENSE)
    );

    private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        List<Category> toCreate = DEFAULT_CATEGORIES.entrySet().stream()
                .filter(entry -> categoryRepository.findByNameIgnoreCase(entry.getKey()).isEmpty())
                .map(entry -> {
                    Category category = new Category();
                    category.setName(entry.getKey());
                    category.setType(entry.getValue());
                    return category;
                })
                .toList();

        if (!toCreate.isEmpty()) {
            categoryRepository.saveAll(toCreate);
            log.info("Seeded {} default categories", toCreate.size());
        }
    }
}
