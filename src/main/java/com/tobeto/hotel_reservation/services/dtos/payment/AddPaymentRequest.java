package com.tobeto.hotel_reservation.services.dtos.payment;

import com.iyzipay.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPaymentRequest {
    private Currency currency;
    private BigDecimal price;
    private BigDecimal paidPrice;
    private String basketId;
    private Long reservationId;
}
