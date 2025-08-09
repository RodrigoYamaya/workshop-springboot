package com.RodriSolution.course.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password) {
}
