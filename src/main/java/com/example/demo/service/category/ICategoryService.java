package com.example.demo.service.category;

import com.example.demo.entity.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();

    Optional<Category> findById(String id);

    void remove(Category category);

    void add(Category category);
}
