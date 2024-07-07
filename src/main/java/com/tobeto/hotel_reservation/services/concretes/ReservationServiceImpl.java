package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Reservation;
import com.tobeto.hotel_reservation.repositories.ReservationRepository;
import com.tobeto.hotel_reservation.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation.services.abstracts.RoomService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.reservation.*;
import com.tobeto.hotel_reservation.services.mappers.ReservationMapper;
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
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final RoomService roomService;

    @Cacheable(cacheNames = "reservations", key = "#root.methodName + #pageNumber + '_' + #pageSize", unless = "#result == null")
    @Override
    public EntityWithPagination getAllReservationWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        Sort sorting = Sort.by(sortDirection, "createdAt");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<Reservation> reservations = reservationRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(reservations);

        List<GetReservationResponse> responses = reservations.stream()
                .map(ReservationMapper.INSTANCE::getResponseFromReservation)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "reservation_id", key = "#root.methodName + #reservationId", unless = "#result == null")
    @Override
    public GetReservationResponse getReservationById(Long reservationId, String language) {
        Reservation foundReservation = findReservationById(reservationId, language);
        return ReservationMapper.INSTANCE.getResponseFromReservation(foundReservation);
    }

    @CacheEvict(cacheNames = {"reservations", "reservation_id"}, allEntries = true)
    @Override
    public AddReservationResponse addReservation(AddReservationRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        roomService.findRoomById(request.getRoomId(), language);
        Reservation reservation = ReservationMapper.INSTANCE.reservationFromAddRequest(request);
        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationMapper.INSTANCE.addResponseFromReservation(savedReservation);
    }

    @CachePut(cacheNames = "reservation_id", key = "'getReservationById' + #reservationId", unless = "#result == null")
    @Override
    public UpdateReservationResponse updateReservationById(Long reservationId, UpdateReservationRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        roomService.findRoomById(request.getRoomId(), language);
        Reservation foundReservation = findReservationById(reservationId, language);
        Reservation updatedReservation = ReservationMapper.INSTANCE.reservationFromUpdateRequest(request);
        updatedReservation.setId(foundReservation.getId());

        Reservation savedReservation = reservationRepository.save(updatedReservation);
        return ReservationMapper.INSTANCE.updateResponseFromReservation(savedReservation);
    }

    @CacheEvict(cacheNames = {"reservations", "reservation_id"}, allEntries = true)
    @Override
    public void deleteReservationById(Long reservationId, String language) {
    Reservation foundReservation = findReservationById(reservationId, language);
    reservationRepository.deleteById(foundReservation.getId());
    }

    private Reservation findReservationById(Long reservationId, String language){
        return reservationRepository.findById(reservationId).orElseThrow(() -> new BusinessException("error.reservationNotFound",language));
    }
}