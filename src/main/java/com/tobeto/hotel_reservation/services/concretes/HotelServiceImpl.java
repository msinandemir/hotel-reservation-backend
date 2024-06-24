package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Hotel;
import com.tobeto.hotel_reservation.repositories.HotelRepository;
import com.tobeto.hotel_reservation.services.abstracts.AddressService;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.hotel.*;
import com.tobeto.hotel_reservation.services.mappers.HotelMapper;
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
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final AddressService addressService;
    private final UserService userService;

    @Cacheable(cacheNames = "hotels", key = "#root.methodName + #pageNumber + '_' + #pageSize", unless = "#result == null")
    @Override
    public EntityWithPagination getAllHotelsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        Sort sorting = Sort.by(sortDirection, "createdAt");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<Hotel> hotels = hotelRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(hotels);

        List<GetHotelResponse> responses = hotels.stream()
                .map(HotelMapper.INSTANCE::getResponseFromHotel)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "hotel_id", key = "#root.methodName + #hotelId", unless = "#result == null")
    @Override
    public GetHotelResponse getHotelById(Long hotelId, String language) {
        Hotel foundHotel = findHotelById(hotelId, language);
        return HotelMapper.INSTANCE.getResponseFromHotel(foundHotel);
    }

    @CacheEvict(cacheNames = {"hotels", "hotel_id"}, allEntries = true)
    @Override
    public AddHotelResponse addHotel(AddHotelRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        addressService.findAddressById(request.getAddressId(), language);
        Hotel hotel = HotelMapper.INSTANCE.hotelFromAddRequest(request);
        Hotel savedHotel = hotelRepository.save(hotel);
        return HotelMapper.INSTANCE.addResponseFromHotel(savedHotel);
    }

    @CachePut(cacheNames = "hotel_id", key = "getHotelById + #request.id", unless = "#result == null")
    @Override
    public UpdateHotelResponse updateHotelById(Long hotelId, UpdateHotelRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        addressService.findAddressById(request.getAddressId(), language);
        Hotel foundHotel = findHotelById(hotelId, language);
        Hotel updatedHotel = HotelMapper.INSTANCE.hotelFromUpdateRequest(request);
        updatedHotel.setId(foundHotel.getId());

        Hotel savedHotel = hotelRepository.save(updatedHotel);
        return HotelMapper.INSTANCE.updateResponseFromHotel(savedHotel);
    }

    @CacheEvict(cacheNames = {"hotels", "hotel_id"}, allEntries = true)
    @Override
    public void deleteHotelById(Long hotelId, String language) {
        Hotel foundHotel = findHotelById(hotelId, language);
        hotelRepository.deleteById(foundHotel.getId());
    }

    @Override
    public Hotel findHotelById(Long hotelId, String language) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new BusinessException("error.hotelNotFound", language));

    }
}
