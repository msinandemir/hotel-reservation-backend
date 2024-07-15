package com.tobeto.hotel_reservation.services.concretes;

import com.iyzipay.model.Payment;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentModel;
import com.tobeto.hotel_reservation.services.abstracts.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IyzicoAdapter implements PaymentGateway {
    private final IyzicoService iyzicoService;

    @Override
    public Payment pay(IyzicoPaymentModel paymentModel, Long reservationId, String language) {
        return iyzicoService.pay(paymentModel, reservationId, language);
    }
}
