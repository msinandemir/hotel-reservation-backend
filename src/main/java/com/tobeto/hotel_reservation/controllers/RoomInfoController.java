package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.RoomInfoService;
import com.tobeto.hotel_reservation.services.dtos.roomInfo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/roomInfos")
@RequiredArgsConstructor
public class RoomInfoController {
    private RoomInfoService roomInfoService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllRoomInfosWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(roomInfoService.getAllRoomInfosWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{roomInfoId}")
    ResponseEntity<GetRoomInfoResponse> getRoomInfoById(@PathVariable Long roomInfoId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomInfoService.getRoomInfoById(roomInfoId, lang));
    }

    @PostMapping
    ResponseEntity<AddRoomInfoResponse> addRoomInfo(@RequestBody AddRoomInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(roomInfoService.addRoomInfo(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{roomInfoId}")
    ResponseEntity<UpdateRoomInfoResponse> updateRoomInfoById(@PathVariable Long roomInfoId, @RequestBody UpdateRoomInfoRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomInfoService.updateRoomInfoById(roomInfoId, request, lang));
    }

    @DeleteMapping("{roomInfoId}")
    void deleteRoomInfoById(@PathVariable Long roomInfoId, @RequestHeader(defaultValue = "en") String lang) {
        roomInfoService.deleteRoomInfoById(roomInfoId, lang);
    }
}
