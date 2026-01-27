package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.CategoryRequestDto;
import com.RodriSolution.course.model.dtos.CategoryResponseDto;
import com.RodriSolution.course.security.SecurityConfig;
import com.RodriSolution.course.service.CategoryService;
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
@Tag(name = "categoria", description = "Endpoints para gerenciamento da categoria dos prdutos.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Operation(summary = "Lista todas as Categorias produto", description = "Retorna uma lista de todos as Categorias produto cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista as categorias do produto,e retornada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @GetMapping("/category/findAll")
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        List<CategoryResponseDto> list = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


    @Operation(summary = "Busca Categoria por ID", description = "Retorna a Categoria produto específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "categoria encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "categoria não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable(value = "id") long id) {
        CategoryResponseDto categoryResponseDto = categoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDto);
    }


    @Operation(summary = "Salvar dados da Categoria", description = "Método para salvar dados de Categoria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria gravada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição (ex: validação falhou)."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @Transactional
    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDto> save(@RequestBody @Valid CategoryRequestDto categoryDto) {
        CategoryResponseDto categorySave = categoryService.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySave);
    }



    @Operation(summary = "Deleta uma categoria", description = "Remove uma categoria no banco de dados com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "categoria deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "categoria não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @Transactional
    @DeleteMapping("category/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        categoryService.deletarCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("category com o ID " + id + " deletado com sucesso.");
    }


}
