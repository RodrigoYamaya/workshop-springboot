package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.ProductRequestDto;
import com.RodriSolution.course.model.dtos.ProductResponseDto;
import com.RodriSolution.course.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService = new ProductService();

    @GetMapping("/product/lista")
    public ResponseEntity <List<ProductResponseDto>> findAll() {
        List<ProductResponseDto> productList = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable(value = "id") long id) {
        ProductResponseDto productResponseDto = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @Transactional
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> save(@RequestBody @Valid ProductRequestDto productDto) {
        ProductResponseDto productSaved = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }

    @Transactional
    @DeleteMapping("product/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        productService.deletarProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("product com o ID " + id + " deletado com sucesso.");
    }

    @Transactional
    @PutMapping("product/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable(value = "id") long id, @RequestBody @Valid ProductRequestDto productDto) {
        ProductResponseDto productUpdate = productService.updateProduct(productDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdate);

    }


}
