package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.RoomService;
import com.tobeto.hotel_reservation.services.dtos.room.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
    private RoomService roomService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllRoomsWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(roomService.getAllRoomsWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{roomId}")
    ResponseEntity<GetRoomResponse> getRoomById(@PathVariable Long roomId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomService.getRoomById(roomId, lang));
    }

    @PostMapping
    ResponseEntity<AddRoomResponse> addRoom(@RequestBody AddRoomRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(roomService.addRoom(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{roomId}")
    ResponseEntity<UpdateRoomResponse> updateRoomById(@PathVariable Long roomId, @RequestBody UpdateRoomRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomService.updateRoomById(roomId, request, lang));
    }

    @DeleteMapping("{roomId}")
    void deleteRoomById(@PathVariable Long roomId, @RequestHeader(defaultValue = "en") String lang) {
        roomService.deleteRoomById(roomId, lang);
    }
}
