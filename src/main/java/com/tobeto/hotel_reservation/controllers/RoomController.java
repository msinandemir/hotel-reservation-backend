package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.enums.RoomType;
import com.tobeto.hotel_reservation.services.abstracts.RoomService;
import com.tobeto.hotel_reservation.services.dtos.room.*;
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

import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
@Validated
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getAllRoomsWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                   @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                   @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                   @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(roomService.getAllRoomsWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{roomId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetRoomResponse> getRoomById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomService.getRoomById(roomId, lang));
    }

    @GetMapping("findAvailableRoomByTypeAndDate")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetRoomResponse> getFindAvailableRoomsByTypeAndDate(@RequestParam RoomType type,
                                                                       @RequestParam LocalDate checkIn,
                                                                       @RequestParam LocalDate checkOut,
                                                                       @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomService.getFindAvailableRoomsByTypeAndDate(type, checkIn, checkOut, lang));
    }

    @GetMapping("filterRoomByHotelId/{hotelId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<AddRoomResponse> addRoom(@RequestBody @Valid AddRoomRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(roomService.addRoom(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{roomId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<UpdateRoomResponse> updateRoomById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomId, @RequestBody @Valid UpdateRoomRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(roomService.updateRoomById(roomId, request, lang));
    }

    @DeleteMapping("{roomId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    void deleteRoomById(@PathVariable @Valid @Positive(message = "validation.positive") Long roomId, @RequestHeader(defaultValue = "en") String lang) {
        roomService.deleteRoomById(roomId, lang);
    }
}
