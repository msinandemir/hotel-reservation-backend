package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.services.dtos.auth.LoginRequest;
import com.tobeto.hotel_reservation.services.dtos.auth.LoginResponse;
import com.tobeto.hotel_reservation.services.dtos.user.AddUserRequest;
import com.tobeto.hotel_reservation.services.dtos.user.AddUserResponse;
import jakarta.mail.MessagingException;

public interface AuthService {
    AddUserResponse register(AddUserRequest request, String language) throws MessagingException;

    LoginResponse login(LoginRequest request, String language);
}
