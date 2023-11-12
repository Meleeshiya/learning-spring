package com.example.firstmvn.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ControllerAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvisor.class);
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleCityNotFoundException(Exception ex, WebRequest request) {
        LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
