package com.tobeto.hotel_reservation.controllers;

import com.iyzipay.model.Payment;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentModel;
import com.tobeto.hotel_reservation.services.abstracts.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentGateway paymentGateway;

    @PostMapping("{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<Payment> pay(@RequestBody IyzicoPaymentModel paymentModel, @PathVariable Long reservationId, @RequestHeader(defaultValue = "en") String lang){
        return ResponseEntity.ok(paymentGateway.pay(paymentModel, reservationId, lang));
    }
}
