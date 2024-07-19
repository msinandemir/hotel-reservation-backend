package com.tobeto.hotel_reservation.services.mappers;

import com.iyzipay.model.Address;
import com.iyzipay.model.Buyer;
import com.iyzipay.model.PaymentCard;
import com.iyzipay.request.CreatePaymentRequest;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentCard;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentModel;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentRequest;
import com.tobeto.hotel_reservation.entities.concretes.Hotel;
import com.tobeto.hotel_reservation.entities.concretes.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IyzicoMapper {
    IyzicoMapper INSTANCE = Mappers.getMapper(IyzicoMapper.class);

    CreatePaymentRequest getPaymentRequestFromIyzicoPaymentRequest(IyzicoPaymentRequest paymentRequest);

    PaymentCard getPaymentCardFromIyzcioPaymentCard(IyzicoPaymentCard iyzicoPaymentCard);

    @Mapping(target = "name", source = "user.firstName")
    @Mapping(target = "surname", source = "user.lastName")
    @Mapping(target = "registrationAddress", source = "user.address.addressLine")
    @Mapping(target = "city", source = "user.address.city.name")
    @Mapping(target = "country", source = "user.address.city.country.name")
    @Mapping(target = "gsmNumber", source = "paymentModel.phoneNumber")
    Buyer getBuyerFromUserAndPaymentModel(User user, IyzicoPaymentModel paymentModel);

    @Mapping(target = "address", source = "address.addressLine")
    @Mapping(target = "country", source = "address.city.country.name")
    @Mapping(target = "city", source = "address.city.name")
    Address getShippingAddressFromUser(User user);

    @Mapping(target = "address", source = "address.addressLine")
    @Mapping(target = "country", source = "address.city.country.name")
    @Mapping(target = "city", source = "address.city.name")
    Address getBillingAddressFromUser(Hotel hotel);
}
