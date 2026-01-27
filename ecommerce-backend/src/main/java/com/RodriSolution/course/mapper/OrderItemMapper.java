package com.RodriSolution.course.mapper;

import com.RodriSolution.course.model.dtos.OrderItemRequestDto;
import com.RodriSolution.course.model.dtos.OrderItemResponseDto;
import com.RodriSolution.course.model.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "price", ignore = true)
    OrderItem toEntity(OrderItemRequestDto dto);


    OrderItemResponseDto toDto(OrderItem entity);
}
