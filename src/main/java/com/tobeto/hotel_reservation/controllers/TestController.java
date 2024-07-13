package com.tobeto.hotel_reservation.controllers;

import com.iyzipay.model.Payment;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentModel;
import com.tobeto.hotel_reservation.services.abstracts.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
    private final PaymentGateway paymentGateway;

    @PostMapping
    Payment pay(@RequestBody IyzicoPaymentModel paymentModel){
        return paymentGateway.pay(paymentModel);
    }
}
