package com.example.restdemo2.endpoint.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

@RestControllerAdvice
public class ExceptionHandlerTranslator {

//    @ExceptionHandler({ConstraintViolationException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> onBindException(ConstraintViolationException e) {
//        return ResponseEntity.badRequest().body(new ArrayList<>(e.getConstraintViolations()));
//    }
}
