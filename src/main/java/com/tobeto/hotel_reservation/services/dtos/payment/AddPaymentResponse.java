package com.tobeto.hotel_reservation.services.dtos.payment;

import com.iyzipay.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPaymentResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private Currency currency;
    private BigDecimal price;
    private BigDecimal paidPrice;
    private String basketId;
    private Long reservationId;
}
