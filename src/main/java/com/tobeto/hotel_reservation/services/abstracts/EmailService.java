package com.tobeto.hotel_reservation.services.abstracts;



public interface EmailService {
    boolean sendWelcomeEmail(String to, String subject, String language);
}
