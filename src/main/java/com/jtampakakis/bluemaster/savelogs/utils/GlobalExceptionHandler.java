package com.jtampakakis.bluemaster.savelogs.utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> unauthorizedException(ValidationException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("VALIDATION ERROR");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
}
