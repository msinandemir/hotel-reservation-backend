package com.tobeto.hotel_reservation.services.concretes;

import com.iyzipay.Options;
import com.iyzipay.model.*;
import com.iyzipay.request.CreatePaymentRequest;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentCard;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentModel;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentRequest;
import com.tobeto.hotel_reservation.entities.concretes.Hotel;
import com.tobeto.hotel_reservation.entities.concretes.Reservation;
import com.tobeto.hotel_reservation.entities.concretes.User;
import com.tobeto.hotel_reservation.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.abstracts.PaymentService;
import com.tobeto.hotel_reservation.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.payment.AddPaymentRequest;
import com.tobeto.hotel_reservation.services.mappers.IyzicoMapper;
import com.tobeto.hotel_reservation.services.mappers.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IyzicoService {
    private final ReservationService reservationService;
    private final PaymentService paymentService;
    private final UserService userService;
    private final HotelService hotelService;

    @Value("${iyzico.api-key}")
    private String API_KEY;

    @Value("${iyzico.secret-key}")
    private String SECRET_KEY;

    @Value("${iyzico.base-url}")
    private String BASE_URL;

    public Payment pay(IyzicoPaymentModel paymentModel, Long reservationId, String language) {
        Reservation foundReservation = reservationService.findReservationById(reservationId, language);
        Options options = getOptions();
        CreatePaymentRequest request = createPaymentRequest(paymentModel.getRequest());
        request.setInstallment(1);
        request.setPrice(foundReservation.getTotalPrice());
        request.setPaidPrice(foundReservation.getTotalPrice());

        PaymentCard card = createPaymentCard(paymentModel.getPaymentCard());
        request.setPaymentCard(card);

        Buyer buyer = createBuyer(paymentModel, language);
        request.setBuyer(buyer);

        Address shippingAddress = createShippingAddress(paymentModel, language);
        request.setShippingAddress(shippingAddress);

        Address billingAddress = createBillingAddress(paymentModel, language);
        request.setBillingAddress(billingAddress);

        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem firstBasketItem = new BasketItem();
        firstBasketItem.setId(UUID.randomUUID().toString());
        firstBasketItem.setName(foundReservation.getUser().getFirstName());
        firstBasketItem.setCategory1("Reservation");
        firstBasketItem.setItemType(BasketItemType.PHYSICAL.name());
        firstBasketItem.setPrice(foundReservation.getTotalPrice());
        basketItems.add(firstBasketItem);
        request.setBasketId(UUID.randomUUID().toString());
        request.setBasketItems(basketItems);

        Payment payment = Payment.create(request, options);

        checkPaymentStatusAndChangeReservationStatus(payment.getStatus(), foundReservation, language);
        savePayment(payment, foundReservation);
        return payment;
    }

    private Options getOptions() {
        Options options = new Options();
        options.setApiKey(API_KEY);
        options.setSecretKey(SECRET_KEY);
        options.setBaseUrl(BASE_URL);
        return options;
    }

    private CreatePaymentRequest createPaymentRequest(IyzicoPaymentRequest request) {
        return IyzicoMapper.INSTANCE.getPaymentRequestFromIyzicoPaymentRequest(request);
    }

    private PaymentCard createPaymentCard(IyzicoPaymentCard iyzicoPaymentCard) {
        return IyzicoMapper.INSTANCE.getPaymentCardFromIyzcioPaymentCard(iyzicoPaymentCard);
    }

    private Buyer createBuyer(IyzicoPaymentModel paymentModel, String language) {
        User foundUser = userService.findUserById(paymentModel.getUserId(), language);
        Buyer buyer = IyzicoMapper.INSTANCE.getBuyerFromUserAndPaymentModel(foundUser, paymentModel);
        buyer.setGsmNumber(paymentModel.getPhoneNumber());
        return buyer;
    }

    private Address createShippingAddress(IyzicoPaymentModel paymentModel, String language) {
        User foundUser = userService.findUserById(paymentModel.getUserId(), language);
        Address address = IyzicoMapper.INSTANCE.getShippingAddressFromUser(foundUser);;
        address.setContactName(foundUser.getFirstName() + " " + foundUser.getLastName());
        return address;
    }
    private Address createBillingAddress(IyzicoPaymentModel paymentModel, String language) {
        Hotel foundHotel = hotelService.findHotelById(paymentModel.getHotelId(), language);
        Address address = IyzicoMapper.INSTANCE.getBillingAddressFromUser(foundHotel);
        address.setContactName(foundHotel.getUser().getFirstName() + " " + foundHotel.getUser().getLastName());
        return address;
    }

    private void checkPaymentStatusAndChangeReservationStatus(String status, Reservation reservation, String language) {
        if (status.equals("success"))
            reservationService.changeReservationStatusById(reservation.getId(), ReservationStatus.PENDING, language);
        else
            reservationService.changeReservationStatusById(reservation.getId(), ReservationStatus.INVALID, language);
    }

    private void savePayment(Payment payment, Reservation foundReservation) {
        com.tobeto.hotel_reservation.entities.concretes.Payment newPayment = PaymentMapper.INSTANCE.paymentFromIyzicoPayment(payment);
        newPayment.setReservation(foundReservation);
        AddPaymentRequest addPaymentRequest = PaymentMapper.INSTANCE.addRequestFromPayment(newPayment);
        paymentService.addPayment(addPaymentRequest);
    }
}
