package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.entities.concretes.Payment;
import com.tobeto.hotel_reservation.repositories.PaymentRepository;
import com.tobeto.hotel_reservation.services.abstracts.PaymentService;
import com.tobeto.hotel_reservation.services.dtos.payment.AddPaymentRequest;
import com.tobeto.hotel_reservation.services.dtos.payment.AddPaymentResponse;
import com.tobeto.hotel_reservation.services.mappers.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public AddPaymentResponse addPayment(AddPaymentRequest request) {
        Payment payment = PaymentMapper.INSTANCE.paymentFromAddRequest(request);
        Payment savedPayment = paymentRepository.save(payment);
        return PaymentMapper.INSTANCE.addResponseFromPayment(savedPayment);
    }
}
