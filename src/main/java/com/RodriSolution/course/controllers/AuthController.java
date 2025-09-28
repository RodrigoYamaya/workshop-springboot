package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.auth.LoginRequestDto;
import com.RodriSolution.course.model.dtos.auth.LoginResponseDto;
import com.RodriSolution.course.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para gerenciamento de login e geração de tokens JWT.")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Autentica um usuário", description = "Gera um token JWT válido para o usuário mediante credenciais corretas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida. Retorna o token JWT e o tempo de expiração."),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos (ex: email ou senha vazios)."),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. Email ou senha incorretos."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno no servidor.")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto logindDto) {
        LoginResponseDto loginResponse = authService.login(logindDto);
        return ResponseEntity.ok(loginResponse);

    }
}
