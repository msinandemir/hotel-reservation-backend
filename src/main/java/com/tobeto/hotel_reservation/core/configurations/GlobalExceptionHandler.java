package com.tobeto.hotel_reservation.core.configurations;

import com.tobeto.hotel_reservation.core.exceptions.exceptionDetails.BusinessExceptionDetails;
import com.tobeto.hotel_reservation.core.exceptions.exceptionDetails.ValidationExceptionDetails;
import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessExceptionDetails> handleRuntimeException(BusinessException exception) {
        return new ResponseEntity<>(new BusinessExceptionDetails(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(new ValidationExceptionDetails(errors), HttpStatus.BAD_REQUEST);
    }
}
