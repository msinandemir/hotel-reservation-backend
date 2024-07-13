package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Address;
import com.tobeto.hotel_reservation.services.dtos.address.*;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AddressService {
    EntityWithPagination getAllAddressWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy);

    GetAddressResponse getAddressById(Long addressId, String language);

    List<GetAddressResponse> getAddressesByUserId(Long userId);

    AddAddressResponse addAddress(AddAddressRequest request, String language);

    UpdateAddressResponse updateAddressById(Long addressId, UpdateAddressRequest request, String language);

    void deleteAddressById(Long addressId, String language);

    Address findAddressById(Long addressId, String language);
}
