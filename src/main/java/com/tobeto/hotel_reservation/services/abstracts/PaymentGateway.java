package com.tobeto.hotel_reservation.services.abstracts;

import com.iyzipay.model.Payment;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentModel;

public interface PaymentGateway {
    Payment pay(IyzicoPaymentModel paymentModel, Long reservationId, String language);
}
