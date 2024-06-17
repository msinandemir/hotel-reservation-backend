package com.tobeto.hotel_reservation.core.exceptions.exceptionDetails;

import com.tobeto.hotel_reservation.core.exceptions.types.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {
    private String title;
    private String detail;
    private ExceptionType type;
}
