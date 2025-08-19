package com.RodriSolution.course.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserResponseDto(
        @NotNull Long id,
        @NotBlank String name,
        @NotBlank  String email,
        @NotBlank String phone){
}
