package com.RodriSolution.course.controllers;

import com.RodriSolution.course.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductService productService = new ProductService();


}
