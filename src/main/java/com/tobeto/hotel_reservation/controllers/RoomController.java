package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.entities.enums.RoomType;
import com.tobeto.hotel_reservation.services.abstracts.RoomService;
import com.tobeto.hotel_reservation.services.dtos.room.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
@Validated
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllRoomsWithPagination(@RequestBody @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(roomService.getAllRoomsWithPagination(paginationRequest));
    }

    @GetMapping("{roomId}")
    ResponseEntity<GetRoomResponse> getRoomById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomService.getRoomById(roomId, lang));
    }

    @GetMapping("findAvailableRoomByTypeAndDate")
    ResponseEntity<GetRoomResponse> getFindAvailableRoomsByTypeAndDate(@RequestBody @Valid FindAvailableRoomsByTypeAndDateRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomService.getFindAvailableRoomsByTypeAndDate(request, lang));
    }

    @GetMapping("filterRoomByHotelId/{hotelId}")
    ResponseEntity<GetRoomResponse> getFilteredRoom(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId,
                                                    @RequestParam(required = false) Integer capacity,
                                                    @RequestParam(required = false) BigDecimal price,
                                                    @RequestParam(required = false) Integer singleBed,
                                                    @RequestParam(required = false) Integer doubleBed,
                                                    @RequestParam(required = false) Integer bunkBed,
                                                    @RequestParam(required = false) RoomType type,
                                                    @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomService.getFilteredRoom(hotelId, capacity, price, singleBed, doubleBed, bunkBed, type, lang));
    }

    @PostMapping
    ResponseEntity<AddRoomResponse> addRoom(@RequestBody @Valid AddRoomRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(roomService.addRoom(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{roomId}")
    ResponseEntity<UpdateRoomResponse> updateRoomById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomId, @RequestBody @Valid UpdateRoomRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomService.updateRoomById(roomId, request, lang));
    }

    @DeleteMapping("{roomId}")
    void deleteRoomById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomId, @RequestHeader(defaultValue = "en") String lang) {
        roomService.deleteRoomById(roomId, lang);
    }
}
