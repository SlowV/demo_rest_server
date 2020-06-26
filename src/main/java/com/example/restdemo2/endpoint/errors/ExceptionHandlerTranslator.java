package com.example.restdemo2.endpoint.errors;

import com.example.restdemo2.domain.rest.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerTranslator {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> onConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return ResponseEntity.badRequest().body(
                new RESTResponse.Error()
                        .setMessage(messageSource.getMessage("Has.exception.handler", null, Locale.getDefault()))
                        .setStatus(HttpStatus.BAD_REQUEST.value())
                        .addErrors(errors)
                        .build()
        );
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> onBindException(BindException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(
                new RESTResponse.Error()
                        .setMessage(messageSource.getMessage("Has.exception.handler", null, Locale.getDefault()))
                        .setStatus(HttpStatus.BAD_REQUEST.value())
                        .addErrors(errors)
                        .build()
        );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(
                new RESTResponse.Error()
                        .setMessage(messageSource.getMessage("Has.exception.handler", null, Locale.getDefault()))
                        .setStatus(HttpStatus.BAD_REQUEST.value())
                        .addErrors(errors)
                        .build()
        );
    }
}
