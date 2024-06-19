package com.tobeto.hotel_reservation.services.abstracts;


public interface EmailService {
    void sendWelcomeEmail(String to, String subject, String language);
}
