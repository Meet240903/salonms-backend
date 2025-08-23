package com.salonms.salonmsBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Errors> handlerForBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(new Errors("API-" + "PRO-" + "400", badRequestException.getMessage(), null, new Extention("", "")), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Errors> handlerForResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return new ResponseEntity<>(new Errors("API-" + "PRO-" + "401", resourceNotFoundException.getMessage(), null, new Extention("", "")), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultException.class)
    public ResponseEntity<Errors> handlerForEmptyResultException(EmptyResultException emptyResultException) {
        return new ResponseEntity<>(new Errors("API-" + "PRO-" + "204", emptyResultException.getMessage(), null, new Extention("", "")), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Errors> handlerForUnauthorizedException(UnauthorizedException unauthorizedException) {
        return new ResponseEntity<>(new Errors("API-" + "PRO-" + "401", unauthorizedException.getMessage(), null, new Extention("", "")), HttpStatus.UNAUTHORIZED);
    }
}
