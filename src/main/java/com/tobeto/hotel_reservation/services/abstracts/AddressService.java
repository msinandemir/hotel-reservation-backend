package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.entities.concretes.Address;
import com.tobeto.hotel_reservation.services.dtos.address.*;

public interface AddressService {
    EntityWithPagination getAllAddressWithPagination(PaginationRequest paginationRequest);

    GetAddressResponse getAddressById(Long addressId, String language);

    AddAddressResponse addAddress(AddAddressRequest request, String language);

    UpdateAddressResponse updateAddressById(Long addressId, UpdateAddressRequest request, String language);

    void deleteAddressById(Long addressId, String language);

    Address findAddressById(Long addressId, String language);
}
