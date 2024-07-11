package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.abstracts.RoomInfoService;
import com.tobeto.hotel_reservation.services.dtos.roomInfo.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/roomInfos")
@RequiredArgsConstructor
@Validated
public class RoomInfoController {
    private final RoomInfoService roomInfoService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllRoomInfosWithPagination(@RequestBody @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(roomInfoService.getAllRoomInfosWithPagination(paginationRequest));
    }

    @GetMapping("{roomInfoId}")
    ResponseEntity<GetRoomInfoResponse> getRoomInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomInfoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomInfoService.getRoomInfoById(roomInfoId, lang));
    }

    @PostMapping
    ResponseEntity<AddRoomInfoResponse> addRoomInfo(@RequestBody @Valid AddRoomInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(roomInfoService.addRoomInfo(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{roomInfoId}")
    ResponseEntity<UpdateRoomInfoResponse> updateRoomInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomInfoId, @RequestBody @Valid UpdateRoomInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomInfoService.updateRoomInfoById(roomInfoId, request, lang));
    }

    @DeleteMapping("{roomInfoId}")
    void deleteRoomInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomInfoId, @RequestHeader(defaultValue = "en") String lang) {
        roomInfoService.deleteRoomInfoById(roomInfoId, lang);
    }
}
