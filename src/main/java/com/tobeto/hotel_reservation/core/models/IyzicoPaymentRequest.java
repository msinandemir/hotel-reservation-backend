package com.tobeto.hotel_reservation.core.models;

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
public class IyzicoPaymentRequest {
    private String locale;
    private BigDecimal price;
    private BigDecimal paidPrice;
    private Currency currency;
    private int installment;
}
