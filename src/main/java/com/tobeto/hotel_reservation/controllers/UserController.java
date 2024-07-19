package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.enums.Role;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.user.*;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<EntityWithPagination> getAllUsersWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                   @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                   @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                   @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(userService.getAllUsersWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetUserResponse> getUserById(@PathVariable @Valid @Positive(message = "validation.positive") Long userId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userService.getUserById(userId, lang));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AddUserResponse> addUser(@RequestBody @Valid AddUserRequest request, @RequestHeader(defaultValue = "en") String lang) throws MessagingException {
        return new ResponseEntity<>(userService.addUser(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<UpdateUserResponse> updateUserById(@PathVariable @Valid @Positive(message = "validation.positive") Long userId, @RequestBody @Valid UpdateUserRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userService.updateUserById(userId, request, lang));
    }

    @PatchMapping("{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ChangeUserRoleResponse> changeUserRoleById(@PathVariable @Valid @Positive(message = "validation.positive") Long userId,
                                                              @RequestBody @Valid @NotNull(message = "validation.notNull") Role role,
                                                              @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userService.changeUserRoleById(userId, role, lang));
    }

    @DeleteMapping("{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteUserById(@PathVariable @Valid @Positive(message = "validation.positive") Long userId, @RequestHeader(defaultValue = "en") String lang) {
        userService.deleteUserById(userId, lang);
    }
}
