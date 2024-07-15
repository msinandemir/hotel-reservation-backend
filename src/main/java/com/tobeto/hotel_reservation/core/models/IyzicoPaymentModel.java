package com.tobeto.hotel_reservation.core.models;

import com.iyzipay.model.Address;
import com.iyzipay.model.Buyer;
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
    private Buyer buyer;
    private Address shippingAddress;
    private Address billingAddress;
}
