package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.UserRequestDto;
import com.RodriSolution.course.model.dtos.UserResponseDto;
import com.RodriSolution.course.security.SecurityConfig;
import com.RodriSolution.course.service.UserService;
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
@RequestMapping("/user")
@Tag(name = "usuario", description = "Endpoints para gerenciamento de usuários.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @Operation(summary = "Busca usuário por ID", description = "Retorna um usuário específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable(value = "id") long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);

    }



    @Transactional
    @PostMapping("/save")
    @Operation(summary = "Salvar dados de Usuário", description = "Método para salvar dados de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário gravado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Email já cadastrado ou dados inválidos."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    public ResponseEntity<UserResponseDto> save(@RequestBody @Valid UserRequestDto userDto) {
        UserResponseDto userSave = userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }




    @Operation(summary = "Deleta um usuário", description = "Remove um usuário do banco de dados com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        userService.deletarUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("user com o ID " + id + " deletado com sucesso.");
    }



    @Operation(summary = "Atualiza um usuário", description = "Atualiza os dados de um usuário existente com base no ID e nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado. A rota exige autenticação."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @Transactional
    @PutMapping("updateUser/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable(value = "id") long id, @RequestBody @Valid UserRequestDto userDto) {
        UserResponseDto userUpdate = userService.update(userDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }
}

