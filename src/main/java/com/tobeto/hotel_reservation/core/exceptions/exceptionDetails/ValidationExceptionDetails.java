package com.tobeto.hotel_reservation.core.exceptions.exceptionDetails;

import com.tobeto.hotel_reservation.core.exceptions.types.ExceptionType;
import com.tobeto.hotel_reservation.core.utils.MessageSource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidationExceptionDetails extends ExceptionDetails {
    private List<String> errors;

    public ValidationExceptionDetails(List<String> errors, String language){
        this.errors = errors;
        setDetail(MessageSource.getMessage(language, "exception.validationDetail"));
        setTitle(MessageSource.getMessage(language, "exception.validationTitle"));
        setType(ExceptionType.VALIDATION_EXCEPTION);
    }
}
