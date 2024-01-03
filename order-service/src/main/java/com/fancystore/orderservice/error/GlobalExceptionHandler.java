package com.fancystore.orderservice.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomIllegalArgumentException.class)
    public ResponseEntity<Object> handleException(CustomIllegalArgumentException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", e.getMessage());
        if (e.getBody() != null) {
            body.put("body", e.getBody());
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
