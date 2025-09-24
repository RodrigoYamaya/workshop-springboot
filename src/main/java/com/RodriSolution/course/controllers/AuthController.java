package com.RodriSolution.course.controllers;

import com.RodriSolution.course.model.dtos.auth.LoginRequestDto;
import com.RodriSolution.course.model.dtos.auth.LoginResponseDto;
import com.RodriSolution.course.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto logindDto) {
        LoginResponseDto loginResponse = authService.login(logindDto);
        return ResponseEntity.ok(loginResponse);

    }
}
