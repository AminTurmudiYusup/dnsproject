package com.example.dnsproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handlerAlreadyExistException(NotFoundException notFoundException){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
        errorMessage.setMessage(notFoundException.getMessage());
        errorMessage.setDate(new Date());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    private final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String > errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String errorMessage=error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("statusCode", String.valueOf(HttpStatus.BAD_REQUEST));
        errors.put("date", format.format(new Date()));
        return errors;
    }
}
