package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.services.dtos.payment.AddPaymentRequest;
import com.tobeto.hotel_reservation.services.dtos.payment.AddPaymentResponse;

public interface PaymentService {
    AddPaymentResponse addPayment(AddPaymentRequest request);
}
