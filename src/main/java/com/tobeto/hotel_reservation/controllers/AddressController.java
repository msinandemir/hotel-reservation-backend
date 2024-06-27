package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.AddressService;
import com.tobeto.hotel_reservation.services.dtos.address.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private AddressService addressService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllAddressWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(addressService.getAllAddressWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{addressId}")
    ResponseEntity<GetAddressResponse> getAddressById(@PathVariable Long addressId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(addressService.getAddressById(addressId, lang));
    }

    @PostMapping
    ResponseEntity<AddAddressResponse> addAddress(@RequestBody AddAddressRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(addressService.addAddress(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{addressId}")
    ResponseEntity<UpdateAddressResponse> updateAddressById(@PathVariable Long addressId, @RequestBody UpdateAddressRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(addressService.updateAddressById(addressId, request, lang));
    }

    @DeleteMapping("{addressId}")
    void deleteAddressById(@PathVariable Long addressId, @RequestHeader(defaultValue = "en") String lang) {
        addressService.deleteAddressById(addressId, lang);
    }
}
