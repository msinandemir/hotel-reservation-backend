package com.tobeto.hotel_reservation.services.concretes;

import com.iyzipay.Options;
import com.iyzipay.model.Address;
import com.iyzipay.model.Buyer;
import com.iyzipay.model.Payment;
import com.iyzipay.model.PaymentCard;
import com.iyzipay.request.CreatePaymentRequest;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentModel;
import com.tobeto.hotel_reservation.core.models.IyzicoPaymentRequest;
import com.tobeto.hotel_reservation.services.mappers.IyzicoMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IyzicoService {
    @Value("${iyzico.api-key}")
    private String API_KEY;

    @Value("${iyzico.secret-key}")
    private String SECRET_KEY;

    @Value("${iyzico.base-url}")
    private String BASE_URL;

    public Payment pay(IyzicoPaymentModel paymentModel) {
        Options options = getOptions();
        CreatePaymentRequest request = createPaymentRequest(paymentModel.getRequest());
        PaymentCard card = createPaymentCard(paymentModel.getPaymentCard());
        request.setPaymentCard(card);

        Buyer buyer = createBuyer(paymentModel.getBuyer());
        request.setBuyer(buyer);

        Address shippingAddress = createAddress(paymentModel.getShippingAddress());
        request.setShippingAddress(shippingAddress);

        Address billingAddress = createAddress(paymentModel.getBillingAddress());
        request.setBillingAddress(billingAddress);

        return Payment.create(request, options);
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

    private PaymentCard createPaymentCard(PaymentCard paymentCard) {
        return IyzicoMapper.INSTANCE.getPaymentCardFromPaymentCard(paymentCard);
    }

    private Buyer createBuyer(Buyer buyer) {
        return IyzicoMapper.INSTANCE.getBuyerFromBuyer(buyer);
    }

    private Address createAddress(Address address) {
        return IyzicoMapper.INSTANCE.getAddressFromAddress(address);
    }
}
