package com.RodriSolution.course.mapper;

import com.RodriSolution.course.model.dtos.PaymentDto;
import com.RodriSolution.course.model.entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDto toDto(Payment payment);
}
