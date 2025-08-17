package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.CategoryRequestDto;
import com.RodriSolution.course.model.dtos.CategoryResponseDto;
import com.RodriSolution.course.model.dtos.ProductRequestDto;
import com.RodriSolution.course.model.dtos.ProductResponseDto;
import com.RodriSolution.course.model.entities.Category;
import com.RodriSolution.course.service.CategoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/category/lista")
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        List<CategoryResponseDto> list = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable(value = "id") long id) {
        CategoryResponseDto categoryResponseDto = categoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDto);
    }

    @Transactional
    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDto> save(@RequestBody @Valid CategoryRequestDto categoryDto) {
        CategoryResponseDto categorySave = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySave);
    }

    @Transactional
    @DeleteMapping("category/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        categoryService.deletarCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("category com o ID " + id + " deletado com sucesso.");
    }


}
