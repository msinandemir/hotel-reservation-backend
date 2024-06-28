package com.tobeto.hotel_reservation.services.abstracts;


import com.tobeto.hotel_reservation.services.dtos.email.SendEmailRequest;

public interface EmailService {
    boolean sendWelcomeEmail(SendEmailRequest sendEmailRequest, String language);
}
