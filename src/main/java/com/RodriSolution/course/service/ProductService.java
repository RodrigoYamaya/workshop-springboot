package com.RodriSolution.course.service;

import com.RodriSolution.course.exceptions.RecursoNaoEncontrado;

import com.RodriSolution.course.mapper.ProductMapper;
import com.RodriSolution.course.model.dtos.ProductRequestDto;
import com.RodriSolution.course.model.dtos.ProductResponseDto;
import com.RodriSolution.course.model.entities.Category;
import com.RodriSolution.course.model.entities.Product;
import com.RodriSolution.course.repositories.CategoryRepository;
import com.RodriSolution.course.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryRepository categoryRepository;

    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponseDto findById(Long id) {
        Product product =  productRepository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontrado("product com ID " + id + " não encontrado."));
        return productMapper.toDto(product);
    }

    @Transactional
    public ProductResponseDto save(ProductRequestDto dto) {
        Set<Category> categories = dto.categoryIds().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RecursoNaoEncontrado("Categoria com ID " + categoryId + " não encontrada.")))
                .collect(Collectors.toSet());

        Product product = productMapper.toEntity(dto);
        product.setCategories(categories);
        Product productSave = productRepository.save(product);
        return productMapper.toDto(productSave);

//        return productMapper.toDto(
//                productRepository.save(
//                        productMapper.toEntity(dto)));
    }

    @Transactional
    public void deletarProduct(long id) {
        if(!productRepository.existsById(id)) {
            throw new RecursoNaoEncontrado("Pet com o ID " + id + " não encontrado");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductResponseDto updateProduct(ProductRequestDto dto, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("product com ID " + id + " não encontrado."));

        updateData(dto,product);
        Product productSave= productRepository.save(product);
        return productMapper.toDto(productSave);

    }


    private void updateData(ProductRequestDto dto,Product product) {
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setImgUrl(dto.imgUrl());

        if(dto.categoryIds() != null && !dto.categoryIds().isEmpty()) {
            Set<Category> categories = dto.categoryIds().stream()
                    .map(categoryId -> categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RecursoNaoEncontrado("Categoria com ID " + categoryId + " não encontrada.")))
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }
    }

}
