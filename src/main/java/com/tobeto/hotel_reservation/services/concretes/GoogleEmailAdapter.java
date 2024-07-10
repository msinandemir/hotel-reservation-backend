package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.ReservationCancelEmail;
import com.tobeto.hotel_reservation.core.models.ReservationConfirmEmail;
import com.tobeto.hotel_reservation.core.models.WelcomeEmail;
import com.tobeto.hotel_reservation.services.abstracts.EmailGateway;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleEmailAdapter implements EmailGateway {
    private final GoogleEmailService googleEmailService;

    @Override
    public boolean sendWelcomeEmail(WelcomeEmail welcomeEmail, String language) throws MessagingException {
        return googleEmailService.sendWelcomeEmail(welcomeEmail, language);
    }

    @Override
    public boolean sendReservationCancellationEmail(ReservationCancelEmail reservationCancelEmail, String language) throws MessagingException {
        return googleEmailService.sendReservationCancellationEmail(reservationCancelEmail, language);
    }

    @Override
    public boolean sendReservationConfirmationEmail(ReservationConfirmEmail reservationConfirmEmail, String language) throws MessagingException {
        return googleEmailService.senReservationConfirmationEmail(reservationConfirmEmail, language);
    }
}
