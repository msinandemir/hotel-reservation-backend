package com.tobeto.hotel_reservation.core.exceptions.exceptionDetails;

import com.tobeto.hotel_reservation.core.exceptions.types.ExceptionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessExceptionDetails extends ExceptionDetails{
    public BusinessExceptionDetails(String details){
        setDetail(details);
        setTitle("Business Rule Violation!");
        setType(ExceptionType.BUSINESS_EXCEPTION);
    }
}
