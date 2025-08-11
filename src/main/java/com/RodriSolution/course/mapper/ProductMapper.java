package com.RodriSolution.course.mapper;

import com.RodriSolution.course.model.dtos.ProductRequestDto;
import com.RodriSolution.course.model.dtos.ProductResponseDto;
import com.RodriSolution.course.model.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Product toEntity(ProductRequestDto dto);

    ProductResponseDto toDto(Product entity);
}
