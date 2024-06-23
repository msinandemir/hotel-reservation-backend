package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.UserInfoService;
import com.tobeto.hotel_reservation.services.dtos.userInfo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/userInfos")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllUserInfosWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(userInfoService.getAllUserInfosWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{userInfoId}")
    ResponseEntity<GetUserInfoResponse> getUserInfoById(@PathVariable Long userInfoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userInfoService.getUserInfoById(userInfoId, lang));
    }

    @PostMapping
    ResponseEntity<AddUserInfoResponse> addUserInfo(@RequestBody AddUserInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(userInfoService.addUserInfo(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{userInfoId}")
    ResponseEntity<UpdateUserInfoResponse> updateUserInfoById(@PathVariable Long userInfoId, @RequestBody UpdateUserInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(userInfoService.updateUserInfoById(userInfoId, request, lang));
    }

    @DeleteMapping("{userInfoId}")
    void deleteUserInfoById(@PathVariable Long userInfoId, @RequestHeader(defaultValue = "en") String lang) {
        userInfoService.deleteUserInfoById(userInfoId, lang);
    }
}
