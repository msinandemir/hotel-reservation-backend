package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.HotelInfo;
import com.tobeto.hotel_reservation.repositories.HotelInfoRepository;
import com.tobeto.hotel_reservation.services.abstracts.HotelInfoService;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.dtos.hotelInfo.*;
import com.tobeto.hotel_reservation.services.mappers.HotelInfoMapper;
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
public class HotelInfoServiceImpl implements HotelInfoService {
    private final HotelInfoRepository hotelInfoRepository;
    private final HotelService hotelService;

    @Cacheable(cacheNames = "hotel_infos", key = "#root.methodName + #pageNumber + '_' + #pageSize", unless = "#result == null")
    @Override
    public EntityWithPagination getAllHotelInfosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        Sort sorting = Sort.by(sortDirection, "createdAt");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<HotelInfo> hotelInfos = hotelInfoRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(hotelInfos);

        List<GetHotelInfoResponse> responses = hotelInfos.stream()
                .map(HotelInfoMapper.INSTANCE::getResponseFromHotelInfo)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "hotel_info_id", key = "#root.methodName + #hotelInfoId", unless = "#result == null")
    @Override
    public GetHotelInfoResponse getHotelInfoById(Long hotelInfoId, String language) {
        HotelInfo foundHotelInfo = findHotelInfoById(hotelInfoId, language);
        return HotelInfoMapper.INSTANCE.getResponseFromHotelInfo(foundHotelInfo);
    }

    @CacheEvict(cacheNames = {"hotel_infos", "hotel_info_id"}, allEntries = true)
    @Override
    public AddHotelInfoResponse addHotelInfo(AddHotelInfoRequest request, String language) {
        hotelService.findHotelById(request.getHotelId(), language);
        HotelInfo hotelInfo = HotelInfoMapper.INSTANCE.hotelInfoFromAddRequest(request);
        HotelInfo savedHotelInfo = hotelInfoRepository.save(hotelInfo);
        return HotelInfoMapper.INSTANCE.addResponseFromHotelInfo(savedHotelInfo);
    }

    @CachePut(cacheNames = "hotel_info_id", key = "getHotelInfoById + #request.id", unless = "#result == null")
    @Override
    public UpdateHotelInfoResponse updateHotelInfoById(Long hotelInfoId, UpdateHotelInfoRequest request, String language) {
        hotelService.findHotelById(request.getHotelId(), language);
        HotelInfo foundHotelInfo = findHotelInfoById(hotelInfoId, language);
        HotelInfo updatedHotelInfo = HotelInfoMapper.INSTANCE.hotelInfoFromUpdateRequest(request);
        updatedHotelInfo.setId(foundHotelInfo.getId());

        HotelInfo savedHotelInfo = hotelInfoRepository.save(updatedHotelInfo);
        return HotelInfoMapper.INSTANCE.updateResponseFromHotelInfo(savedHotelInfo);
    }

    @CacheEvict(cacheNames = {"hotel_infos", "hotel_info_id"}, allEntries = true)
    @Override
    public void deleteHotelInfoById(Long hotelInfoId, String language) {
        HotelInfo foundHotelInfo = findHotelInfoById(hotelInfoId, language);
        hotelInfoRepository.deleteById(foundHotelInfo.getId());
    }

    private HotelInfo findHotelInfoById(Long hotelInfoId, String language) {
        return hotelInfoRepository.findById(hotelInfoId).orElseThrow(() -> new BusinessException("error.hotelInfoNotFound", language));
    }
}
