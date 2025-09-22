package com.RodriSolution.course.service;

import com.RodriSolution.course.model.dtos.auth.LoginRequestDto;
import com.RodriSolution.course.model.dtos.auth.LoginResponseDto;
import com.RodriSolution.course.model.entities.User;
import com.RodriSolution.course.repositories.UserRepository;
import com.RodriSolution.course.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha invalido"));

        //verifaremos senha//
        if(!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new IllegalArgumentException("Senha invalida ou email");
        }

        //gerar token jwt
        String token = jwtService.generateToken(user);

        //calcular experação
        Instant expiresAt = jwtService.generateExpirationDate();

        return new LoginResponseDto(token, user.getUserRole().name(), expiresAt);

        //continua amanha caso termine o teste.
        // finalizar JwtAuthenticationFilter.
        // finalzar endPoint controller authController
    }
}
