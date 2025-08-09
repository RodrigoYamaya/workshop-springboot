package com.RodriSolution.course.model.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
        @NotBlank String name){
}
