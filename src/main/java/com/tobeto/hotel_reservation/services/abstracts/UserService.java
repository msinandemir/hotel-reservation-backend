package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.User;
import com.tobeto.hotel_reservation.services.dtos.user.*;
import org.springframework.data.domain.Sort;

public interface UserService {
    EntityWithPagination getAllUsersWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetUserResponse getUserById(Long userId, String language);

    AddUserResponse addUser(AddUserRequest request);

    UpdateUserResponse updateUserById(Long userId, UpdateUserRequest request, String language);

    void deleteUserById(Long userId, String language);

    User findUserById(Long userId, String language);
}
