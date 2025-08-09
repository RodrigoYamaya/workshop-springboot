package com.RodriSolution.course.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryResponseDto(
        @NotNull Long id,
        @NotBlank String name) {
}
