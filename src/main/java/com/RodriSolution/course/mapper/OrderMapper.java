package com.RodriSolution.course.mapper;

import com.RodriSolution.course.model.dtos.OrderRequestDto;
import com.RodriSolution.course.model.dtos.OrderResponseDto;
import com.RodriSolution.course.model.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "moment", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "payment", ignore = true)
    Order toEntity(OrderRequestDto dto);


    OrderResponseDto toDto(Order entity);
}
