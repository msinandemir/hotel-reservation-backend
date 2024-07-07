package com.tobeto.hotel_reservation.core.exceptions.exceptionDetails;

import com.tobeto.hotel_reservation.core.exceptions.types.ExceptionType;
import com.tobeto.hotel_reservation.core.utils.MessageSource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessagingExceptionDetails extends ExceptionDetails{
    public MessagingExceptionDetails(String language){
        setDetail(MessageSource.getMessage(language,"email.sendError"));
        setTitle(MessageSource.getMessage(language,"exception.messagingTitle"));
        setType(ExceptionType.MESSAGING_EXCEPTION);
    }
}
