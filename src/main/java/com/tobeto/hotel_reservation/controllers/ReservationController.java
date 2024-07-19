package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation.services.dtos.reservation.*;
import jakarta.mail.MessagingException;
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

@RestController
@RequestMapping("api/v1/reservations")
@RequiredArgsConstructor
@Validated
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<EntityWithPagination> getAllReservationWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                         @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                         @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                         @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(reservationService.getAllReservationWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetReservationResponse> getReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(reservationService.getReservationById(reservationId, lang));
    }

    @GetMapping("reservationsByUserId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getReservationsByUserId(@PathVariable @Valid @Positive(message = "validation.positive") Long userId,
                                                                 @RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                 @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                 @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                 @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId, pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("reservationsByHotelId/{hotelId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getReservationsByHotelId(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId,
                                                                  @RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                  @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                  @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                  @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(reservationService.getReservationsByHotelId(hotelId, pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("totalRevenue/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<BigDecimal> getTotalRevenueByUserId(@PathVariable @Valid @Positive(message = "validation.positive") Long userId) {
        return ResponseEntity.ok(reservationService.getTotalRevenueByUserId(userId));
    }

    @GetMapping("pastReservations/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getPastReservationsByUserId(@PathVariable @Valid @Positive(message = "validation.positive") Long userId,
                                                                     @RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                     @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                     @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                     @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(reservationService.getPastReservationsByUserId(userId, pageNumber, pageSize, direction, sortBy));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<AddReservationResponse> addReservation(@RequestBody @Valid AddReservationRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(reservationService.addReservation(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<UpdateReservationResponse> updateReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestBody @Valid UpdateReservationRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(reservationService.updateReservationById(reservationId, request, lang));
    }

    @PatchMapping("{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<ChangeReservationStatusResponse> changeReservationStatusById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestBody ReservationStatus status, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(reservationService.changeReservationStatusById(reservationId, status, lang));
    }

    @PatchMapping("confirmReservationById/{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    ResponseEntity<ChangeReservationStatusResponse> confirmReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestHeader(defaultValue = "en") String lang) throws MessagingException {
        return ResponseEntity.ok(reservationService.confirmReservationById(reservationId, lang));
    }

    @PatchMapping("cancelReservationById/{reservationId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<ChangeReservationStatusResponse> cancelReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestHeader(defaultValue = "en") String lang) throws MessagingException {
        return ResponseEntity.ok(reservationService.cancelReservationById(reservationId, lang));
    }

    @DeleteMapping("{reservationId}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteReservationById(@PathVariable @Valid @Positive(message = "validation.positive") Long reservationId, @RequestHeader(defaultValue = "en") String lang) {
        reservationService.deleteReservationById(reservationId, lang);
    }
}
