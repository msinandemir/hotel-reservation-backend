package com.tobeto.hotel_reservation.core.exceptions.exceptionDetails;

import com.tobeto.hotel_reservation.core.exceptions.types.ExceptionType;
import com.tobeto.hotel_reservation.core.utils.MessageSource;

public class IOExceptionDetails extends ExceptionDetails {
    public IOExceptionDetails(String language) {
        setDetail(MessageSource.getMessage(language, "exception.ioDetail"));
        setTitle(MessageSource.getMessage(language, "exception.ioTitle"));
        setType(ExceptionType.IO_EXCEPTION);
    }
}
