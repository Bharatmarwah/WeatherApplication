package com.bm.WeatherApp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "status", 400,
                        "error", ex.getMessage()
                ));
    }
}
