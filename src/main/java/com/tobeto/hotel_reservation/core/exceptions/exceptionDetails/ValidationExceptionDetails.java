package com.tobeto.hotel_reservation.core.exceptions.exceptionDetails;

import com.tobeto.hotel_reservation.core.exceptions.types.ExceptionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidationExceptionDetails extends ExceptionDetails {
    private List<String> errors;

    public ValidationExceptionDetails(List<String> errors){
        this.errors = errors;
        setDetail("One or More Validation Error(s)!");
        setTitle("Validation Rule Violation!");
        setType(ExceptionType.VALIDATION_EXCEPTION);
    }
}
