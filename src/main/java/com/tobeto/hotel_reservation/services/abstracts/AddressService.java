package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Address;
import com.tobeto.hotel_reservation.services.dtos.address.*;
import org.springframework.data.domain.Sort;

public interface AddressService {
    EntityWithPagination getAllAddressWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetAddressResponse getAddressById(Long addressId, String language);

    AddAddressResponse addAddress(AddAddressRequest request, String language);

    UpdateAddressResponse updateAddressById(Long addressId, UpdateAddressRequest request, String language);

    void deleteAddressById(Long addressId, String language);

    Address findAddressById(Long addressId, String language);
}
