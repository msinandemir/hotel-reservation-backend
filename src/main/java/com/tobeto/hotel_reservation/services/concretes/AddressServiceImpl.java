package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Address;
import com.tobeto.hotel_reservation.repositories.AddressRepository;
import com.tobeto.hotel_reservation.services.abstracts.AddressService;
import com.tobeto.hotel_reservation.services.abstracts.CityService;
import com.tobeto.hotel_reservation.services.dtos.address.*;
import com.tobeto.hotel_reservation.services.mappers.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CityService cityService;

    @Cacheable(cacheNames = "addresses", key = "#root.methodName + #pageNumber + '_' + #pageSize", unless = "#result == null")
    @Override
    public EntityWithPagination getAllAddressWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        Sort sorting = Sort.by(sortDirection, "createdAt");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<Address> addresses = addressRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(addresses);

        List<GetAddressResponse> responses = addresses.stream()
                .map(AddressMapper.INSTANCE::getResponseFromAddress)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "address_id", key = "#root.methodName + #addressId", unless = "#result == null")
    @Override
    public GetAddressResponse getAddressById(Long addressId, String language) {
        Address foundAddress = findAddressById(addressId, language);
        return AddressMapper.INSTANCE.getResponseFromAddress(foundAddress);
    }

    @CacheEvict(cacheNames = {"addresses", "address_id"}, allEntries = true)
    @Override
    public AddAddressResponse addAddress(AddAddressRequest request, String language) {
        cityService.findCityById(request.getCityId(), language);
        Address address = AddressMapper.INSTANCE.addressFromAddRequest(request);
        Address savedAddress = addressRepository.save(address);
        return AddressMapper.INSTANCE.addResponseFromAddress(savedAddress);
    }

    @CachePut(cacheNames = "address_id", key = "getAddressById + #request.id", unless = "#result == null")
    @Override
    public UpdateAddressResponse updateAddressById(Long addressId, UpdateAddressRequest request, String language) {
        cityService.findCityById(request.getCityId(), language);
        Address foundAddress = findAddressById(addressId, language);
        Address updatedAddress = AddressMapper.INSTANCE.addressFromUpdateRequest(request);
        updatedAddress.setId(foundAddress.getId());

        Address savedAddress = addressRepository.save(updatedAddress);
        return AddressMapper.INSTANCE.updateResponseFromAddress(savedAddress);
    }

    @CacheEvict(cacheNames = {"addresses", "address_id"}, allEntries = true)
    @Override
    public void deleteAddressById(Long addressId, String language) {
        Address foundAddress = findAddressById(addressId, language);
        addressRepository.deleteById(foundAddress.getId());
    }

    @Override
    public Address findAddressById(Long addressId, String language){
        return addressRepository.findById(addressId).orElseThrow(() -> new BusinessException("error.addressNotFound", language));
    }
}
