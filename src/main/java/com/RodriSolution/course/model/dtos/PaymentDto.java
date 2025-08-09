package com.RodriSolution.course.model.dtos;

import java.time.Instant;

public record PaymentDto(
        Long id,
        Instant instant
) {
}
