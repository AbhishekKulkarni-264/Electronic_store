package com.example.electronic_store.exceptions;

import com.example.electronic_store.dtos.ApiResponsemessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //handle res not found exception

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponsemessage> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
   return new ResponseEntity<>(ApiResponsemessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build(),HttpStatus.NOT_FOUND);
    }

    //Method argument not valid

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotFoundException(MethodArgumentNotValidException ex){
       List<ObjectError> allErrors= ex.getBindingResult().getAllErrors();
       Map<String,Object> response=new HashMap<>();
       allErrors.stream().forEach(objectError->{
         String message=  objectError.getDefaultMessage();
           String field=((FieldError)objectError).getField();
           response.put(field,message);
       });
       return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
