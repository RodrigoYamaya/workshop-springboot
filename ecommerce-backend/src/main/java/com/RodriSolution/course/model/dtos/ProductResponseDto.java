package com.RodriSolution.course.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public record ProductResponseDto(
       @NotNull Long id,
       @NotBlank String name,
       @NotBlank String description,
       @NotNull @Positive Double price,
       @NotBlank String imgUrl,
       Set<CategoryResponseDto> categories) {
}
