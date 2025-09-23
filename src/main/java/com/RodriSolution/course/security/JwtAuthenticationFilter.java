package com.RodriSolution.course.security;

import com.RodriSolution.course.model.entities.User;
import com.RodriSolution.course.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Extrairemos token no header Authorization
        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null || authHeader.startsWith("Bearer ")) {
           filterChain.doFilter(request,response);
           return;
        }

        final String token = authHeader.substring(7);

        //validaremos token
        if(!jwtService.isTokenValid(token)) {
            filterChain.doFilter(request,response);
            return;
        }

        //buscaremos usuario pelo ID no token
        Long userId = jwtService.getUserIdFromToken(token);
        User user    = userRepository.findById(userId).orElse(null);
        if(user == null) {
            filterChain.doFilter(request,response);
            return;
        }

        //criaremos autenticação no spring security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user,null,
                Collections.singletonList(()-> "ROLE_" + user.getUserRole().name()));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        //setar contextp de autenticação
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //continuar o filtro
        filterChain.doFilter(request,response);
    }
}
