package com.example.demo.controller;

import com.example.demo.entity.model.Category;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private ICategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategoryList() {
        List<Category> categories = this.categoryService.findAll();
        if (!categories.isEmpty()) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        this.categoryService.add(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping("/remove/{cid}")
    public ResponseEntity<Void> removeCategory(@PathVariable("cid") String cid) {
        Optional<Category> category = categoryService.findById(cid);
        if (category.isPresent()) {
            categoryService.remove(category.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
