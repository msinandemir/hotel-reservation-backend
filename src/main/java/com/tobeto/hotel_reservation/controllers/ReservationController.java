package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation.services.dtos.reservation.*;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/reservations")
@RequiredArgsConstructor
@Validated
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllReservationWithPagination(@RequestParam @Valid @Positive(message = "validation.positive") int pageNumber, @RequestParam @Valid @Positive(message = "validation.positive") int pageSize) {
        return ResponseEntity.ok(reservationService.getAllReservationWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{reservationId}")
    ResponseEntity<GetReservationResponse> getReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(reservationService.getReservationById(reservationId, lang));
    }

    @GetMapping("reservationsByUserId/{userId}")
    ResponseEntity<EntityWithPagination> getReservationsByUserId(@PathVariable @Valid @Positive(message = "validation.positive") Long userId, @RequestParam @Valid @Positive(message = "validation.positive") int pageNumber, @RequestParam @Valid @Positive(message = "validation.positive") int pageSize) {
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId, pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("reservationsByHotelId/{hotelId}")
    ResponseEntity<EntityWithPagination> getReservationsByHotelId(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId, @RequestParam @Valid @Positive(message = "validation.positive") int pageNumber, @RequestParam @Valid @Positive(message = "validation.positive") int pageSize) {
        return ResponseEntity.ok(reservationService.getReservationsByHotelId(hotelId, pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("totalRevenue/{userId}")
    ResponseEntity<BigDecimal> getTotalRevenueByUserId(@PathVariable @Valid @Positive(message = "validation.positive") Long userId){
        return ResponseEntity.ok(reservationService.getTotalRevenueByUserId(userId));
    }

    @PostMapping
    ResponseEntity<AddReservationResponse> addReservation(@RequestBody @Valid AddReservationRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(reservationService.addReservation(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{reservationId}")
    ResponseEntity<UpdateReservationResponse> updateReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestBody @Valid UpdateReservationRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(reservationService.updateReservationById(reservationId, request, lang));
    }

    @PatchMapping("{reservationId}")
    ResponseEntity<ChangeReservationStatusResponse> changeReservationStatusById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestBody ReservationStatus status, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(reservationService.changeReservationStatusById(reservationId, status, lang));
    }

    @PatchMapping("confirmReservationById/{reservationId}")
    ResponseEntity<ChangeReservationStatusResponse> confirmReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestHeader(defaultValue = "en") String lang) throws MessagingException {
        return ResponseEntity.ok(reservationService.confirmReservationById(reservationId, lang));
    }

    @PatchMapping("cancelReservationById/{reservationId}")
    ResponseEntity<ChangeReservationStatusResponse> cancelReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestHeader(defaultValue = "en") String lang) throws MessagingException {
        return ResponseEntity.ok(reservationService.cancelReservationById(reservationId, lang));
    }

    @DeleteMapping("{reservationId}")
    void deleteReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestHeader(defaultValue = "en") String lang) {
        reservationService.deleteReservationById(reservationId, lang);
    }
}
