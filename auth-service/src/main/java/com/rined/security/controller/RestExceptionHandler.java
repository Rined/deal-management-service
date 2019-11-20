package com.rined.security.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.rined.security.dto.response.ErrorResponseDto;
import com.rined.security.exception.AlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler({AlreadyExistsException.class})
    public ErrorResponseDto alreadyExists(AlreadyExistsException e) {
        return new ErrorResponseDto("Conflict!", e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class})
    public ErrorResponseDto badRequest(BindException e) {
        StringJoiner joiner = new StringJoiner("! ", "", "!");
        e.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .forEach(joiner::add);
        return new ErrorResponseDto("Bad request!", joiner.toString());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({JsonMappingException.class})
    public ErrorResponseDto badRequest(JsonMappingException e) {
        return new ErrorResponseDto("Bad request!", e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponseDto badRequest(MethodArgumentNotValidException e) {
        return new ErrorResponseDto("Bad request!", e.getMessage());
    }

}
