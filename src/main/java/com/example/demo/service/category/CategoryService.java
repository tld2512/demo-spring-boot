package com.example.demo.service.category;

import com.example.demo.entity.model.Category;
import com.example.demo.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(String id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public void remove(Category category) {
        this.categoryRepository.delete(category);
    }

    @Override
    public void add(Category category) {
        this.categoryRepository.save(category);
    }
}

