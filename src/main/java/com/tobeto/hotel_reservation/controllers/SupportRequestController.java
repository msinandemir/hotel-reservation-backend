package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.SupportRequestService;
import com.tobeto.hotel_reservation.services.dtos.supportRequest.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/supportRequests")
@RequiredArgsConstructor
@Validated
public class SupportRequestController {
    private final SupportRequestService supportRequestService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllSupportRequestsWithPagination(@RequestParam @Valid @Positive(message = "validation.positive") int pageNumber, @RequestParam @Valid @Positive(message = "validation.positive") int pageSize) {
        return ResponseEntity.ok(supportRequestService.getAllSupportRequestsWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{supportRequestId}")
    ResponseEntity<GetSupportRequestResponse> getSupportRequestById(@PathVariable @Valid @Positive(message = "validation.positive") Long supportRequestId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(supportRequestService.getSupportRequestById(supportRequestId, lang));
    }

    @GetMapping("supportRequestsByUserId/{userId}")
    ResponseEntity<EntityWithPagination> getSupportRequestByUserIdWithPagination(@PathVariable @Valid @Positive(message = "validation.positive") Long userId, @RequestParam @Valid @Positive(message = "validation.positive") int pageNumber, @RequestParam @Valid @Positive(message = "validation.positive") int pageSize) {
        return ResponseEntity.ok(supportRequestService.getSupportRequestByUserIdWithPagination(userId, pageNumber, pageSize, Sort.Direction.DESC));
    }

    @PostMapping
    ResponseEntity<AddSupportRequestResponse> addSupportRequest(@RequestBody @Valid AddSupportRequestRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(supportRequestService.addSupportRequest(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{supportRequestId}")
    ResponseEntity<UpdateSupportRequestResponse> updateSupportRequestById(@PathVariable @Valid @Positive(message = "validation.positive") Long supportRequestId, @RequestBody @Valid UpdateSupportRequestRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(supportRequestService.updateSupportRequestById(supportRequestId, request, lang));
    }

    @DeleteMapping("{supportRequestId}")
    void deleteSupportRequestById(@PathVariable @Valid @Positive(message = "validation.positive") Long supportRequestId, @RequestHeader(defaultValue = "en") String lang) {
        supportRequestService.deleteSupportRequestById(supportRequestId, lang);
    }
}
