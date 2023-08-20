package com.karen.exception;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorResponse handleDuplicateKeyException(DuplicateKeyException ex) {
        return buildErrorResponse("DuplicateKeyException: " + ex.getCause());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse handleNullPointerException(NullPointerException ex) {
        return buildErrorResponse("NullPointerException: " + ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleNotFoundException(EntityNotFoundException ex) {
        return buildErrorResponse("EntityNotFoundException: " + ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleBadRequestException(IllegalArgumentException ex) {
        return buildErrorResponse("IllegalArgumentException: " + ex.getMessage());
    }

    private ErrorResponse buildErrorResponse(String message) {
        return new ErrorResponse(
                message,
                ZonedDateTime.now()
        );
    }
}
