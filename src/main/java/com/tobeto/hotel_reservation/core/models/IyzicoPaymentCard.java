package com.tobeto.hotel_reservation.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IyzicoPaymentCard {
    private String cardHolderName;
    private String cardNumber;
    private String expireYear;
    private String expireMonth;
    private String cvc;
    private int registerCard;
}
