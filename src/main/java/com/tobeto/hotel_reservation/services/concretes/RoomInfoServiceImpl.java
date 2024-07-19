package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.RoomInfo;
import com.tobeto.hotel_reservation.repositories.RoomInfoRepository;
import com.tobeto.hotel_reservation.services.abstracts.RoomInfoService;
import com.tobeto.hotel_reservation.services.abstracts.RoomService;
import com.tobeto.hotel_reservation.services.dtos.roomInfo.*;
import com.tobeto.hotel_reservation.services.mappers.RoomInfoMapper;
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
public class RoomInfoServiceImpl implements RoomInfoService {
    private final RoomInfoRepository roomInfoRepository;
    private final RoomService roomService;

    @Cacheable(cacheNames = "room_infos", key = "#root.methodName + #pageNumber + '_' + #pageSize + '_' + #sortDirection + '_' + #sortBy", unless = "#result == null")
    @Override
    public EntityWithPagination getAllRoomInfosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy) {
        Sort sorting = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<RoomInfo> roomInfos = roomInfoRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(roomInfos);

        List<GetRoomInfoResponse> responses = roomInfos.stream()
                .map(RoomInfoMapper.INSTANCE::getResponseFromRoomInfo)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "room_info_id", key = "#root.methodName + #roomInfoId", unless = "#result == null")
    @Override
    public GetRoomInfoResponse getRoomInfoById(Long roomInfoId, String language) {
        RoomInfo foundRoomInfo = findRoomInfoById(roomInfoId, language);
        return RoomInfoMapper.INSTANCE.getResponseFromRoomInfo(foundRoomInfo);
    }

    @Override
    public GetRoomInfoResponse getRoomInfoByRoomId(Long roomId, String language) {
        RoomInfo foundRoomInfo = roomInfoRepository.findByRoomId(roomId).orElseThrow(() -> new BusinessException("error.roomInfoNotFound", language));
        return RoomInfoMapper.INSTANCE.getResponseFromRoomInfo(foundRoomInfo);
    }

    @CacheEvict(cacheNames = {"room_info_id", "room_infos"}, allEntries = true)
    @Override
    public AddRoomInfoResponse addRoomInfo(AddRoomInfoRequest request, String language) {
        roomService.findRoomById(request.getRoomId(), language);
        RoomInfo roomInfo = RoomInfoMapper.INSTANCE.roomInfoFromAddRequest(request);
        RoomInfo savedRoomInfo = roomInfoRepository.save(roomInfo);
        return RoomInfoMapper.INSTANCE.addResponseFromRoomInfo(savedRoomInfo);
    }

    @CachePut(cacheNames = "room_info_id", key = "'getRoomInfoById' + #roomInfoId", unless = "#result == null")
    @Override
    public UpdateRoomInfoResponse updateRoomInfoById(Long roomInfoId, UpdateRoomInfoRequest request, String language) {
        roomService.findRoomById(request.getRoomId(), language);
        RoomInfo foundRoomInfo = findRoomInfoById(roomInfoId, language);
        RoomInfo updatedRoomInfo = RoomInfoMapper.INSTANCE.roomInfoFromUpdateRequest(request);
        updatedRoomInfo.setId(foundRoomInfo.getId());

        RoomInfo savedRoomInfo = roomInfoRepository.save(updatedRoomInfo);
        return RoomInfoMapper.INSTANCE.updateResponseFromRoomInfo(savedRoomInfo);
    }

    @CacheEvict(cacheNames = {"room_info_id", "room_infos"}, allEntries = true)
    @Override
    public void deleteRoomInfoById(Long roomInfoId, String language) {
        RoomInfo foundRoomInfo = findRoomInfoById(roomInfoId, language);
        roomInfoRepository.deleteById(foundRoomInfo.getId());
    }

    private RoomInfo findRoomInfoById(Long roomInfoId, String language) {
        return roomInfoRepository.findById(roomInfoId).orElseThrow(() -> new BusinessException("error.roomInfoNotFound", language));
    }
}
