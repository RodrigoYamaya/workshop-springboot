package com.RodriSolution.course.handler;

import lombok.*;

import java.time.LocalDateTime;


@Builder
@Data
public class ErrorResponse {
    LocalDateTime timestamp;
    int status;
    String error;
    String message;
    String path;
}
