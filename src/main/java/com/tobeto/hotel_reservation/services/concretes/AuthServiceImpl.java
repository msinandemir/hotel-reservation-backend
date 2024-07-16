package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.utils.JwtUtil;
import com.tobeto.hotel_reservation.entities.concretes.User;
import com.tobeto.hotel_reservation.services.abstracts.AuthService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.auth.LoginRequest;
import com.tobeto.hotel_reservation.services.dtos.auth.LoginResponse;
import com.tobeto.hotel_reservation.services.dtos.user.AddUserRequest;
import com.tobeto.hotel_reservation.services.dtos.user.AddUserResponse;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public AddUserResponse register(AddUserRequest request, String language) throws MessagingException {
        return userService.addUser(request, language);
    }

    @Override
    public LoginResponse login(LoginRequest request, String language) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(request.getEmail(), null);
            User loadUser = userService.loadUserByEmail(request.getEmail(), language);
            return createLoginResponse(token, loadUser);
        }
        throw new BusinessException("error.invalidUser", language);
    }

    private LoginResponse createLoginResponse(String token, User loadUser) {
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUserId(loadUser.getId());
        response.setRole(loadUser.getAuthorities().get(0));
        return response;
    }
}
