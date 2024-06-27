package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.SupportRequestService;
import com.tobeto.hotel_reservation.services.dtos.supportRequest.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/supportRequests")
@RequiredArgsConstructor
public class SupportRequestController {
    private SupportRequestService supportRequestService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllSupportRequestsWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(supportRequestService.getAllSupportRequestsWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{supportRequestId}")
    ResponseEntity<GetSupportRequestResponse> getSupportRequestById(@PathVariable Long supportRequestId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(supportRequestService.getSupportRequestById(supportRequestId, lang));
    }

    @PostMapping
    ResponseEntity<AddSupportRequestResponse> addSupportRequest(@RequestBody AddSupportRequestRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(supportRequestService.addSupportRequest(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{supportRequestId}")
    ResponseEntity<UpdateSupportRequestResponse> updateSupportRequestById(@PathVariable Long supportRequestId, @RequestBody UpdateSupportRequestRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(supportRequestService.updateSupportRequestById(supportRequestId, request, lang));
    }

    @DeleteMapping("{supportRequestId}")
    void deleteSupportRequestById(@PathVariable Long supportRequestId, @RequestHeader(defaultValue = "en") String lang) {
        supportRequestService.deleteSupportRequestById(supportRequestId, lang);
    }
}
