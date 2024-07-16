package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.User;
import com.tobeto.hotel_reservation.entities.enums.Role;
import com.tobeto.hotel_reservation.services.dtos.user.*;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    EntityWithPagination getAllUsersWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy);

    GetUserResponse getUserById(Long userId, String language);

    AddUserResponse addUser(AddUserRequest request, String language) throws MessagingException;

    UpdateUserResponse updateUserById(Long userId, UpdateUserRequest request, String language);

    ChangeUserRoleResponse changeUserRoleById(Long userId, Role role, String language);

    void deleteUserById(Long userId, String language);

    User findUserById(Long userId, String language);

    User loadUserByEmail(String email, String language);
}
