package com.RodriSolution.course.service;

import com.RodriSolution.course.exceptions.RecursoNaoEncontrado;
import com.RodriSolution.course.mapper.CategoryMapper;
import com.RodriSolution.course.model.dtos.CategoryResponseDto;
import com.RodriSolution.course.model.entities.Category;
import com.RodriSolution.course.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryResponseDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryResponseDto findById(long id) {
        Category category =  categoryRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("category  com ID" + id + "nao encontrado"));
        return categoryMapper.toDto(category);
    }
}
