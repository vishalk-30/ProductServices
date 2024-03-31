package org.scaler.productservice.exceptionHandler;

import org.scaler.productservice.dtos.ProductNotFoundExceptionDto;
import org.scaler.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException exception){
        ProductNotFoundExceptionDto exceptionDto = new ProductNotFoundExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        exceptionDto.setStatus("Failure");
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }



}
