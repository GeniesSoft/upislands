package com.geniessoft.backend.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException e) {

        return ResponseEntity.badRequest().body(
                e.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList())
        );
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<List<String>> handleEntityNotFoundException(EntityNotFoundException e) {

        return ResponseEntity.badRequest().body(
                List.of(e.getMessage())
        );
    }

    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseEntity<List<String>> handleEntityExistsException(EntityExistsException e) {

        return ResponseEntity.badRequest().body(
                List.of(e.getMessage())
        );
    }

}
