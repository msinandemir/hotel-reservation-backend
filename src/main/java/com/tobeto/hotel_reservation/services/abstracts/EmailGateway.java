package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.ReservationCancelEmail;
import com.tobeto.hotel_reservation.core.models.ReservationConfirmEmail;
import com.tobeto.hotel_reservation.core.models.WelcomeEmail;
import jakarta.mail.MessagingException;

public interface EmailGateway {
    boolean sendWelcomeEmail(WelcomeEmail welcomeEmail, String language) throws MessagingException;

    boolean sendReservationCancellationEmail(ReservationCancelEmail reservationCancelEmail, String language) throws MessagingException;

    boolean sendReservationConfirmationEmail(ReservationConfirmEmail reservationConfirmEmail, String language) throws MessagingException;
}
