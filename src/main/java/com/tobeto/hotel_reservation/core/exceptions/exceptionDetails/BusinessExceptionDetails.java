package com.tobeto.hotel_reservation.core.exceptions.exceptionDetails;

import com.tobeto.hotel_reservation.core.exceptions.types.ExceptionType;
import com.tobeto.hotel_reservation.core.utils.MessageSource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessExceptionDetails extends ExceptionDetails{
    public BusinessExceptionDetails(String details, String language){
        setDetail(details);
        setTitle(MessageSource.getMessage(language,"exception.businessTitle"));
        setType(ExceptionType.BUSINESS_EXCEPTION);
    }
}
