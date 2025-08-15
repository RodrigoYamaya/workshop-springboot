package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.entities.Category;
import com.RodriSolution.course.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category/lista")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> findById(@PathVariable(value = "id") long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

}
