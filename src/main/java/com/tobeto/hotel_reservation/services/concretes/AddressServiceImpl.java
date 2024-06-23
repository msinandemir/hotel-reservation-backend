package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Address;
import com.tobeto.hotel_reservation.repositories.AddressRepository;
import com.tobeto.hotel_reservation.services.abstracts.AddressService;
import com.tobeto.hotel_reservation.services.dtos.address.AddAddressRequest;
import com.tobeto.hotel_reservation.services.dtos.address.AddAddressResponse;
import com.tobeto.hotel_reservation.services.dtos.address.GetAddressResponse;
import com.tobeto.hotel_reservation.services.dtos.address.UpdateAddressResponse;
import com.tobeto.hotel_reservation.services.dtos.user.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public EntityWithPagination getAllAddressWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetAddressResponse getAddressById(Long addressId, String language) {
        return null;
    }

    @Override
    public AddAddressResponse addAddress(AddAddressRequest request) {
        return null;
    }

    @Override
    public UpdateAddressResponse updateAddressById(Long addressId, UpdateUserRequest request, String language) {
        return null;
    }

    @Override
    public void deleteAddressById(Long addressId, String language) {

    }

    @Override
    public Address findAddressById(Long addressId, String language){
        return addressRepository.findById(addressId).orElseThrow(() -> new BusinessException("error.addressNotFound", language));
    }
}
