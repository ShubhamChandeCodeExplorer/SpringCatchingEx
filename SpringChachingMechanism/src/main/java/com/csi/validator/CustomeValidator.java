package com.csi.validator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomeValidator extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> map=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String Field=((FieldError) error).getField();
            String FieldMessage=error.getDefaultMessage();
            map.put(Field,FieldMessage);
        });
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
}
