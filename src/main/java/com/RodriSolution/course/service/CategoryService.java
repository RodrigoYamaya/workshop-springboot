package com.RodriSolution.course.service;

import com.RodriSolution.course.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.course.model.entities.Category;
import com.RodriSolution.course.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(long id) {
        return categoryRepository.findAll();
    }

    @Transactional
    public Category findById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("category  com ID" + id + "nao encontrado"));
    }
}
