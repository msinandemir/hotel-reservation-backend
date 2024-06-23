package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.address.AddAddressRequest;
import com.tobeto.hotel_reservation.services.dtos.address.AddAddressResponse;
import com.tobeto.hotel_reservation.services.dtos.address.GetAddressResponse;
import com.tobeto.hotel_reservation.services.dtos.address.UpdateAddressResponse;
import com.tobeto.hotel_reservation.services.dtos.user.UpdateUserRequest;
import org.springframework.data.domain.Sort;

public interface AddressService {
    EntityWithPagination getAllAddressWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);
    GetAddressResponse getAddressById(Long addressId, String language);
    AddAddressResponse addAddress(AddAddressRequest request);
    UpdateAddressResponse updateAddressById(Long addressId, UpdateUserRequest request, String language);
    void deleteAddressById(Long addressId, String language);
}
