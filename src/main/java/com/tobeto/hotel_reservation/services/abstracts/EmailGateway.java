package com.tobeto.hotel_reservation.services.abstracts;

import jakarta.mail.MessagingException;

public interface EmailGateway {
    boolean sendWelcomeEmail(String to, String subject, String language) throws MessagingException;

    boolean sendReservationCancellationEmail(String to, String subject, String language) throws MessagingException;

    boolean sendReservationConfirmationEmail(String to, String subject, String language) throws MessagingException;
}
