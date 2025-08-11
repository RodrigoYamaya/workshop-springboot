package com.RodriSolution.course.mapper;

import com.RodriSolution.course.model.dtos.CategoryRequestDto;
import com.RodriSolution.course.model.dtos.CategoryResponseDto;
import com.RodriSolution.course.model.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryRequestDto dto);

    CategoryResponseDto toDto(Category entity);
}
