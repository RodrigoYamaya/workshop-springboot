package com.RodriSolution.course.model.dtos;

import com.RodriSolution.course.model.enums.OrderStatus;

public record UpdateStatusRequestDto(OrderStatus newStatus) {
}
