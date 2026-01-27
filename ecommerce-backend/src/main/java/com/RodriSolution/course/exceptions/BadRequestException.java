package com.RodriSolution.course.exceptions;


public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }
}
