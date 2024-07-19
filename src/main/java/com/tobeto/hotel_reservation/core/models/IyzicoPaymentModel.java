package com.tobeto.hotel_reservation.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IyzicoPaymentModel {
    private IyzicoPaymentRequest request;
    private IyzicoPaymentCard paymentCard;
    private Long userId;
    private Long hotelId;
    private String identityNumber;
    private String phoneNumber;
    private String ip;
}
