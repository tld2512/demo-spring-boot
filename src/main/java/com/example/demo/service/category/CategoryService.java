package com.example.demo.service.category;

import com.example.demo.entity.model.Category;
import com.example.demo.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private final ICategoryRepository ICategoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository ICategoryRepository) {
        this.ICategoryRepository = ICategoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.ICategoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return this.ICategoryRepository.findById(String.valueOf(id));
    }

    @Override
    public void remove(Category category) {
        this.ICategoryRepository.delete(category);
    }

    @Override
    public void add(Category category) {
        this.ICategoryRepository.save(category);
    }
}

