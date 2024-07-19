package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.SupportRequestService;
import com.tobeto.hotel_reservation.services.dtos.supportRequest.*;
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

@RestController
@RequestMapping("api/v1/supportRequests")
@RequiredArgsConstructor
@Validated
public class SupportRequestController {
    private final SupportRequestService supportRequestService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<EntityWithPagination> getAllSupportRequestsWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                             @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                             @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                             @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(supportRequestService.getAllSupportRequestsWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{supportRequestId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetSupportRequestResponse> getSupportRequestById(@PathVariable @Valid @Positive(message = "validation.positive") Long supportRequestId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(supportRequestService.getSupportRequestById(supportRequestId, lang));
    }

    @GetMapping("supportRequestsByUserId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getSupportRequestByUserIdWithPagination(@PathVariable @Valid @Positive(message = "validation.positive") Long userId,
                                                                                 @RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                                 @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                                 @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                                 @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(supportRequestService.getSupportRequestByUserIdWithPagination(userId, pageNumber, pageSize, direction, sortBy));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<AddSupportRequestResponse> addSupportRequest(@RequestBody @Valid AddSupportRequestRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(supportRequestService.addSupportRequest(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{supportRequestId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<UpdateSupportRequestResponse> updateSupportRequestById(@PathVariable @Valid @Positive(message = "validation.positive") Long supportRequestId, @RequestBody @Valid UpdateSupportRequestRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(supportRequestService.updateSupportRequestById(supportRequestId, request, lang));
    }

    @DeleteMapping("{supportRequestId}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteSupportRequestById(@PathVariable @Valid @Positive(message = "validation.positive") Long supportRequestId, @RequestHeader(defaultValue = "en") String lang) {
        supportRequestService.deleteSupportRequestById(supportRequestId, lang);
    }
}
