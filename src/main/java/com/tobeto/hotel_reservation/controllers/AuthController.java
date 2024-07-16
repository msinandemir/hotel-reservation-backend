package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.services.abstracts.AuthService;
import com.tobeto.hotel_reservation.services.dtos.auth.LoginRequest;
import com.tobeto.hotel_reservation.services.dtos.auth.LoginResponse;
import com.tobeto.hotel_reservation.services.dtos.user.AddUserRequest;
import com.tobeto.hotel_reservation.services.dtos.user.AddUserResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AddUserResponse> register(@RequestBody @Valid AddUserRequest request,
                                                    @RequestHeader(defaultValue = "en") String lang) throws MessagingException {
        return new ResponseEntity<>(authService.register(request, lang), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request,
                                               @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(authService.login(request, lang), HttpStatus.OK);
    }
}
