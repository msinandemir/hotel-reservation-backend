package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllUsersWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(userService.getAllUsersWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{userId}")
    ResponseEntity<GetUserResponse> getUserById(@PathVariable Long userId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userService.getUserById(userId, lang));
    }

    @PostMapping
    ResponseEntity<AddUserResponse> addUser(@RequestBody AddUserRequest request) {
        return new ResponseEntity<>(userService.addUser(request), HttpStatus.CREATED);
    }

    @PutMapping("{userId}")
    ResponseEntity<UpdateUserResponse> updateUserById(@PathVariable Long userId, @RequestBody UpdateUserRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userService.updateUserById(userId, request, lang));
    }

    @DeleteMapping("{userId}")
    void deleteUserById(@PathVariable Long userId, @RequestHeader(defaultValue = "en") String lang) {
        userService.deleteUserById(userId, lang);
    }
}
