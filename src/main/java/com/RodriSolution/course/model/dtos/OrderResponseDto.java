package com.RodriSolution.course.model.dtos;

import com.RodriSolution.course.model.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
import java.util.Set;

public record OrderResponseDto(
        @NotNull Long id,
        Instant moment,
        OrderStatus orderStatus,
        UserResponseDto client,
        Set<OrderItemResponseDto> items,
        PaymentDto payment,
        @Positive Double total) {
}
