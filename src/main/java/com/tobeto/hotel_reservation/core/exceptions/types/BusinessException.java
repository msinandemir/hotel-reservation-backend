package com.tobeto.hotel_reservation.core.exceptions.types;

import com.tobeto.hotel_reservation.core.utils.MessageSource;

public class BusinessException extends RuntimeException {
    public BusinessException(String message, String language) {
        super(MessageSource.getMessage(language, message));
    }
}
