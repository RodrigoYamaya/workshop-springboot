package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.ProductRequestDto;
import com.RodriSolution.course.model.dtos.ProductResponseDto;
import com.RodriSolution.course.security.SecurityConfig;
import com.RodriSolution.course.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Produto", description = "Endpoints para gerenciamento de produtos no catálogo.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class ProductController {

    @Autowired
    ProductService productService = new ProductService();

    @Operation(summary = "Lista todos os produtos", description = "Retorna uma lista de todos os produtos do catálogo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @GetMapping("/product/findAll")
    public ResponseEntity <List<ProductResponseDto>> findAll() {
        List<ProductResponseDto> productList = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    

    @Operation(summary = "Busca produto por ID", description = "Retorna um produto específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable(value = "id") long id) {
        ProductResponseDto productResponseDto = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @Operation(summary = "Salva um novo produto", description = "Adiciona um novo produto ao catálogo. Requer autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos (ex: campos obrigatórios ausentes)."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Usuário não tem permissão para criar produtos."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @Transactional
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> save(@RequestBody @Valid ProductRequestDto productDto) {
        ProductResponseDto productSaved = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }


    @Operation(summary = "Deleta um produto", description = "Remove um produto do catálogo com base no ID. Requer autenticação e, geralmente, permissão de Admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. Usuário não tem permissão para deletar."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @Transactional
    @DeleteMapping("product/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        productService.deletarProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("product com o ID " + id + " deletado com sucesso.");
    }


    @Operation(summary = "Atualiza um produto", description = "Atualiza os dados de um produto existente com base no ID e nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para atualização."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @Transactional
    @PutMapping("product/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable(value = "id") long id, @RequestBody @Valid ProductRequestDto productDto) {
        ProductResponseDto productUpdate = productService.updateProduct(productDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdate);

    }


}
