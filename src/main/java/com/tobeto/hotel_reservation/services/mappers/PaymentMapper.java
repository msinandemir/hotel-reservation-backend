package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Payment;
import com.tobeto.hotel_reservation.services.dtos.payment.AddPaymentRequest;
import com.tobeto.hotel_reservation.services.dtos.payment.AddPaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(target = "reservation.id", source = "reservationId")
    Payment paymentFromAddRequest(AddPaymentRequest request);

    @Mapping(source = "reservation.id", target = "reservationId")
    AddPaymentResponse addResponseFromPayment(Payment payment);

    com.tobeto.hotel_reservation.entities.concretes.Payment paymentFromIyzicoPayment(com.iyzipay.model.Payment payment);

    @Mapping(source = "reservation.id", target = "reservationId")
    AddPaymentRequest addRequestFromPayment(Payment payment);
}
