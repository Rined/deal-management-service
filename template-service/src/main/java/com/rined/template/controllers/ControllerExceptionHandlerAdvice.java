package com.rined.template.controllers;

import com.rined.template.controllers.dto.ErrorDto;
import com.rined.template.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.StringJoiner;

@ResponseBody
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandlerAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ErrorDto convertError(HttpMessageNotReadableException e) {
        return createErrorDto("Bean convert error!", e);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ErrorDto notFound(NotFoundException e) {
        return createErrorDto("Not found!", e);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorDto notFound(MethodArgumentNotValidException e) {
        return createErrorDto("Validation error!", e.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class})
    public ErrorDto notFound(BindException e) {
        StringJoiner joiner = new StringJoiner("! ", "", "!");
        e.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .forEach(joiner::add);
        return createErrorDto("Validation error!", joiner.toString());
    }

    private ErrorDto createErrorDto(String reason, Exception e) {
        return createErrorDto(reason, e.getMessage());
    }

    private ErrorDto createErrorDto(String reason, String description) {
        return new ErrorDto(reason, description);
    }
}
