package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.RoomInfoService;
import com.tobeto.hotel_reservation.services.dtos.roomInfo.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    ResponseEntity<EntityWithPagination> getAllRoomInfosWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                       @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                       @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                       @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(roomInfoService.getAllRoomInfosWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{roomInfoId}")
    ResponseEntity<GetRoomInfoResponse> getRoomInfoById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomInfoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomInfoService.getRoomInfoById(roomInfoId, lang));
    }

    @GetMapping("getRoomInfoByRoomId/{roomId}")
    ResponseEntity<GetRoomInfoResponse> getRoomInfoByRoomId(@PathVariable @Valid @Positive(message = "validation.positive") Long roomId,
                                                            @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomInfoService.getRoomInfoByRoomId(roomId, lang));
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
