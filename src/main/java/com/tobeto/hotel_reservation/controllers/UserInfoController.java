package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.UserInfoService;
import com.tobeto.hotel_reservation.services.dtos.userInfo.*;
import jakarta.validation.Valid;
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
@RequestMapping("api/v1/userInfos")
@RequiredArgsConstructor
@Validated
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<EntityWithPagination> getAllUserInfosWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                       @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                       @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                       @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(userInfoService.getAllUserInfosWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{userInfoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetUserInfoResponse> getUserInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long userInfoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userInfoService.getUserInfoById(userInfoId, lang));
    }

    @GetMapping("userInfoByUserId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetUserInfoResponse> getUserInfoByUserId(@PathVariable @Valid @Positive(message = "validation.positive") Long userId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userInfoService.getUserInfoByUserId(userId, lang));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<AddUserInfoResponse> addUserInfo(@RequestBody @Valid AddUserInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(userInfoService.addUserInfo(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{userInfoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<UpdateUserInfoResponse> updateUserInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long userInfoId, @RequestBody @Valid UpdateUserInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userInfoService.updateUserInfoById(userInfoId, request, lang));
    }

    @DeleteMapping("{userInfoId}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteUserInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long userInfoId, @RequestHeader(defaultValue = "en") String lang) {
        userInfoService.deleteUserInfoById(userInfoId, lang);
    }
}
