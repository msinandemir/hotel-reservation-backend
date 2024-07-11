package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.entities.concretes.User;
import com.tobeto.hotel_reservation.services.dtos.user.*;
import jakarta.mail.MessagingException;

public interface UserService {
    EntityWithPagination getAllUsersWithPagination(PaginationRequest paginationRequest);

    GetUserResponse getUserById(Long userId, String language);

    AddUserResponse addUser(AddUserRequest request, String language) throws MessagingException;

    UpdateUserResponse updateUserById(Long userId, UpdateUserRequest request, String language);

    void deleteUserById(Long userId, String language);

    User findUserById(Long userId, String language);
}
