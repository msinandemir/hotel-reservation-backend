package com.tobeto.hotel_reservation.core.configurations;

import com.tobeto.hotel_reservation.core.exceptions.exceptionDetails.BusinessExceptionDetails;
import com.tobeto.hotel_reservation.core.exceptions.exceptionDetails.IOExceptionDetails;
import com.tobeto.hotel_reservation.core.exceptions.exceptionDetails.MessagingExceptionDetails;
import com.tobeto.hotel_reservation.core.exceptions.exceptionDetails.ValidationExceptionDetails;
import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import jakarta.mail.MessagingException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessExceptionDetails> handleRuntimeException(BusinessException exception, WebRequest request) {
        String language = request.getHeader("lang");
        return new ResponseEntity<>(new BusinessExceptionDetails(exception.getMessage(), language), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationExceptionDetails> handleValidationException(ConstraintViolationException exception, WebRequest request) {
        String language = getLanguageFromRequest(request);

        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        return new ResponseEntity<>(new ValidationExceptionDetails(errors, language), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<MessagingExceptionDetails> handleMessagingException(WebRequest request) {
        String language = getLanguageFromRequest(request);
        return new ResponseEntity<>(new MessagingExceptionDetails(language), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<IOExceptionDetails> handleIOException(WebRequest request) {
        String language = getLanguageFromRequest(request);
        return new ResponseEntity<>(new IOExceptionDetails(language), HttpStatus.BAD_REQUEST);
    }

    private String getLanguageFromRequest(WebRequest request) {
        return request.getHeader("lang");
    }
}
