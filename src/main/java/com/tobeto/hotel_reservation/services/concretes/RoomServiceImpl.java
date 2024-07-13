package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.utils.RoomSpecifications;
import com.tobeto.hotel_reservation.entities.concretes.Room;
import com.tobeto.hotel_reservation.entities.enums.RoomType;
import com.tobeto.hotel_reservation.repositories.RoomRepository;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.abstracts.RoomService;
import com.tobeto.hotel_reservation.services.dtos.room.*;
import com.tobeto.hotel_reservation.services.mappers.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    @Cacheable(cacheNames = "rooms", key = "#root.methodName + #pageNumber + '_' + #pageSize + '_' + #sortDirection + '_' + #sortBy", unless = "#result == null")
    @Override
    public EntityWithPagination getAllRoomsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy) {
        Sort sorting = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<Room> rooms = roomRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(rooms);

        List<GetRoomResponse> responses = rooms.stream()
                .map(RoomMapper.INSTANCE::getResponseFromRoom)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "room_id", key = "#root.methodName + #roomId", unless = "#result == null")
    @Override
    public GetRoomResponse getRoomById(Long roomId, String language) {
        Room foundRoom = findRoomById(roomId, language);
        return RoomMapper.INSTANCE.getResponseFromRoom(foundRoom);
    }

    @Override
    public GetRoomResponse getFindAvailableRoomsByTypeAndDate(RoomType type, LocalDate checkIn, LocalDate checkOut, String language) {
        List<Room> availableRooms = roomRepository.findAvailableRoomsByTypeAndDate(type, checkIn, checkOut);
        Room getFirstRoom = getFirstRoomInAvailableRooms(availableRooms, language);
        return RoomMapper.INSTANCE.getResponseFromRoom(getFirstRoom);
    }

    @Override
    public GetRoomResponse getFilteredRoom(Long hotelId, Integer capacity, BigDecimal price, Integer singleBed, Integer doubleBed, Integer bunkBed, RoomType type, String language) {
        Specification<Room> roomSpecification = Specification.where(RoomSpecifications.hasCapacity(capacity))
                .and(RoomSpecifications.hasPrice(price))
                .and(RoomSpecifications.isAvailable(true))
                .and(RoomSpecifications.hasSingleBed(singleBed))
                .and(RoomSpecifications.hasDoubleBed(doubleBed))
                .and(RoomSpecifications.hasBunkBed(bunkBed))
                .and(RoomSpecifications.hasType(type))
                .and(RoomSpecifications.hasHotelId(hotelId));

        List<Room> availableRooms = roomRepository.findAll(roomSpecification);
        Room getFirstRoom = getFirstRoomInAvailableRooms(availableRooms, language);
        return RoomMapper.INSTANCE.getResponseFromRoom(getFirstRoom);
    }

    @CacheEvict(cacheNames = {"room_id", "rooms"}, allEntries = true)
    @Override
    public AddRoomResponse addRoom(AddRoomRequest request, String language) {
        hotelService.findHotelById(request.getHotelId(), language);
        Room room = RoomMapper.INSTANCE.roomFromAddRequest(request);
        Room savedRoom = roomRepository.save(room);
        return RoomMapper.INSTANCE.addResponseFromRoom(savedRoom);
    }

    @CachePut(cacheNames = "room_id", key = "'getRoomById' + #roomId", unless = "#result == null")
    @Override
    public UpdateRoomResponse updateRoomById(Long roomId, UpdateRoomRequest request, String language) {
        hotelService.findHotelById(request.getHotelId(), language);
        Room foundRoom = findRoomById(roomId, language);
        Room updatedRoom = RoomMapper.INSTANCE.roomFromUpdateRequest(request);
        updatedRoom.setId(foundRoom.getId());

        Room savedRoom = roomRepository.save(updatedRoom);
        return RoomMapper.INSTANCE.updateResponseFromRoom(savedRoom);
    }

    @CacheEvict(cacheNames = {"room_id", "rooms"}, allEntries = true)
    @Override
    public void deleteRoomById(Long roomId, String language) {
        Room foundRoom = findRoomById(roomId, language);
        roomRepository.deleteById(foundRoom.getId());
    }

    @Scheduled(cron = "0 0 12 * * ?")
    @Override
    public void updateRoomAvailabilityScheduled() {
        List<Room> getRoomsPastCheckoutAndNotAvailable = roomRepository.findRoomsPastCheckoutAndNotAvailable(LocalDate.now());
        List<Room> updatedRooms = getRoomsPastCheckoutAndNotAvailable.stream()
                .peek(room -> room.setAvailability(true))
                .toList();
        roomRepository.saveAll(updatedRooms);
    }

    @Override
    public void updateRoomAvailability(Long roomId, boolean availability, String language) {
        Room foundRoom = findRoomById(roomId, language);
        foundRoom.setAvailability(availability);
        roomRepository.save(foundRoom);
    }

    @Override
    public Room findRoomById(Long roomId, String language) {
        return roomRepository.findById(roomId).orElseThrow(() -> new BusinessException("error.roomNotFound", language));
    }

    private Room getFirstRoomInAvailableRooms(List<Room> availableRooms, String language) {
        if (availableRooms.isEmpty())
            throw new BusinessException("error.availableRoom", language);
        else
            return availableRooms.get(0);
    }
}
