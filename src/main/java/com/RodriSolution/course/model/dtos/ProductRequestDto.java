package com.RodriSolution.course.model.dtos;

import jakarta.validation.constraints.*;

import java.util.Set;

public record ProductRequestDto(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @Positive Double price,
        @NotBlank String imgUrl,
        @NotEmpty(message = "O produto deve pertencer a pelo menos uma categoria")
        Set<@Positive Long> categoriaIds

) {
}
