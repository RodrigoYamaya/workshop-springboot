package com.RodriSolution.course.model.dtos;

import jakarta.validation.constraints.NotNull;

public record OrderItemResponseDto(
        @NotNull Long id,
        ProductResponseDto product,
        Double quantity,
        Double subTotal) {
}
