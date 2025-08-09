package com.RodriSolution.course.model.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDto(
        @NotNull Long clientId,
        @NotEmpty List<OrderItemRequestDto> items
) {
}
