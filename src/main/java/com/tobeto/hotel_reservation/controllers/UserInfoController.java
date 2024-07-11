package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.abstracts.UserInfoService;
import com.tobeto.hotel_reservation.services.dtos.userInfo.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/userInfos")
@RequiredArgsConstructor
@Validated
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllUserInfosWithPagination(@RequestBody @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(userInfoService.getAllUserInfosWithPagination(paginationRequest));
    }

    @GetMapping("{userInfoId}")
    ResponseEntity<GetUserInfoResponse> getUserInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long userInfoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userInfoService.getUserInfoById(userInfoId, lang));
    }

    @GetMapping("userInfoByUserId/{userId}")
    ResponseEntity<GetUserInfoResponse> getUserInfoByUserId(@PathVariable @Valid @Positive(message = "validation.positive") Long userId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userInfoService.getUserInfoByUserId(userId, lang));
    }

    @PostMapping
    ResponseEntity<AddUserInfoResponse> addUserInfo(@RequestBody @Valid AddUserInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(userInfoService.addUserInfo(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{userInfoId}")
    ResponseEntity<UpdateUserInfoResponse> updateUserInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long userInfoId, @RequestBody @Valid UpdateUserInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userInfoService.updateUserInfoById(userInfoId, request, lang));
    }

    @DeleteMapping("{userInfoId}")
    void deleteUserInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long userInfoId, @RequestHeader(defaultValue = "en") String lang) {
        userInfoService.deleteUserInfoById(userInfoId, lang);
    }
}
