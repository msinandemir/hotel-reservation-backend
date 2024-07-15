package com.tobeto.hotel_reservation.services.mappers;

import com.iyzipay.model.Address;
import com.iyzipay.model.Buyer;
import com.iyzipay.model.PaymentCard;
import com.iyzipay.request.CreatePaymentRequest;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentCard;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IyzicoMapper {
    IyzicoMapper INSTANCE = Mappers.getMapper(IyzicoMapper.class);

    CreatePaymentRequest getPaymentRequestFromIyzicoPaymentRequest(IyzicoPaymentRequest paymentRequest);

    PaymentCard getPaymentCardFromIyzcioPaymentCard(IyzicoPaymentCard iyzicoPaymentCard);

    Buyer getBuyerFromBuyer(Buyer buyer);

    Address getAddressFromAddress(Address address);
}
