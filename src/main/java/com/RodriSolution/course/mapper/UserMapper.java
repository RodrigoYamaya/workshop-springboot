package com.RodriSolution.course.mapper;

import com.RodriSolution.course.model.dtos.UserRequestDto;
import com.RodriSolution.course.model.dtos.UserResponseDto;
import com.RodriSolution.course.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDto dto);

    UserResponseDto toDto(User entity);
}
