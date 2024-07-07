package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation.services.dtos.reservation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllReservationWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(reservationService.getAllReservationWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{reservationId}")
    ResponseEntity<GetReservationResponse> getReservationById(@PathVariable Long reservationId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(reservationService.getReservationById(reservationId, lang));
    }

    @PostMapping
    ResponseEntity<AddReservationResponse> addReservation(@RequestBody AddReservationRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(reservationService.addReservation(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{reservationId}")
    ResponseEntity<UpdateReservationResponse> updateReservationById(@PathVariable Long reservationId, @RequestBody UpdateReservationRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(reservationService.updateReservationById(reservationId, request, lang));
    }

    @DeleteMapping("{reservationId}")
    void deleteReservationById(@PathVariable Long reservationId, @RequestHeader(defaultValue = "en") String lang) {
        reservationService.deleteReservationById(reservationId, lang);
    }
}
