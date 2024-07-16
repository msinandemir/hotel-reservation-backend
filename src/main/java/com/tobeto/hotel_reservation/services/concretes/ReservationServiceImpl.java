package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.ReservationCancelEmail;
import com.tobeto.hotel_reservation.core.models.ReservationConfirmEmail;
import com.tobeto.hotel_reservation.entities.concretes.Reservation;
import com.tobeto.hotel_reservation.entities.concretes.Room;
import com.tobeto.hotel_reservation.entities.enums.ReservationStatus;
import com.tobeto.hotel_reservation.repositories.ReservationRepository;
import com.tobeto.hotel_reservation.services.abstracts.EmailGateway;
import com.tobeto.hotel_reservation.services.abstracts.ReservationService;
import com.tobeto.hotel_reservation.services.abstracts.RoomService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.reservation.*;
import com.tobeto.hotel_reservation.services.mappers.EmailMapper;
import com.tobeto.hotel_reservation.services.mappers.ReservationMapper;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final EmailGateway emailGateway;

    @Cacheable(cacheNames = "reservations", key = "#root.methodName + #pageNumber + '_' + #pageSize + '_' + #sortDirection + '_' + #sortBy", unless = "#result == null")
    @Override
    public EntityWithPagination getAllReservationWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy) {
        Sort sorting = Sort.by(sortDirection, sortBy);
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

    @Cacheable(cacheNames = "reservation_user_id", key = "#root.methodName + #userId + '_' + #pageNumber + '_' #pageSize + '_' + #sortDirection + '_' + #sortBy", unless = "#result == null")
    @Override
    public EntityWithPagination getReservationsByUserId(Long userId, int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy) {
        Sort sorting = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<Reservation> reservations = reservationRepository.findByUserId(userId, pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(reservations);

        List<GetReservationResponse> responses = reservations.stream()
                .map(ReservationMapper.INSTANCE::getResponseFromReservation)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "reservation_user_id", key = "#root.methodName + #hotelId + '_' + #pageNumber + '_' #pageSize + '_' + #sortDirection + '_' + #sortBy", unless = "#result == null")
    @Override
    public EntityWithPagination getReservationsByHotelId(Long hotelId, int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy) {
        Sort sorting = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<Reservation> reservations = reservationRepository.findByHotelId(hotelId, pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(reservations);

        List<GetReservationResponse> responses = reservations.stream()
                .map(ReservationMapper.INSTANCE::getResponseFromReservation)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(value = "reservation_total_revenue_user_id", key = "#root.methodName + #userId", unless = "#result == null")
    @Override
    public BigDecimal getTotalRevenueByUserId(Long userId) {
        return reservationRepository.findTotalRevenueByOwnerId(userId);
    }

    @Cacheable(value = "past_reservation_user_id", key = "#root.methodName + #userId + '_' + #pageNumber + '_' + #pageSize + '_' + #sortDirection + '_' + #sortBy", unless = "#result == null")
    @Override
    public EntityWithPagination getPastReservationsByUserId(Long userId, int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy) {
        Sort sorting = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<Reservation> reservations = reservationRepository.findPastReservationsByUserId(userId, LocalDate.now(), pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(reservations);

        List<GetReservationResponse> responses = reservations.stream()
                .map(ReservationMapper.INSTANCE::getResponseFromReservation)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @CacheEvict(cacheNames = {"reservations","reservation_id","reservation_user_id","reservation_user_id","reservation_total_revenue_user_id", "past_reservation_user_id"}, allEntries = true)
    @Override
    public AddReservationResponse addReservation(AddReservationRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        Room foundRoom = roomService.findRoomById(request.getRoomId(), language);

        BigDecimal totalPrice = calculateTotalPrice(request.getCheckIn(), request.getCheckOut(), foundRoom, language);

        Reservation reservation = ReservationMapper.INSTANCE.reservationFromAddRequest(request);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setTotalPrice(totalPrice);

        Reservation savedReservation = reservationRepository.save(reservation);

        roomService.updateRoomAvailability(foundRoom.getId(), false, language);
        return ReservationMapper.INSTANCE.addResponseFromReservation(savedReservation);
    }

    @CachePut(cacheNames = "reservation_id", key = "'getReservationById' + #reservationId", unless = "#result == null")
    @Override
    public UpdateReservationResponse updateReservationById(Long reservationId, UpdateReservationRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        Room foundRoom = roomService.findRoomById(request.getRoomId(), language);

        BigDecimal totalPrice = calculateTotalPrice(request.getCheckIn(), request.getCheckOut(), foundRoom, language);

        Reservation foundReservation = findReservationById(reservationId, language);
        Reservation updatedReservation = ReservationMapper.INSTANCE.reservationFromUpdateRequest(request);
        updatedReservation.setId(foundReservation.getId());
        updatedReservation.setTotalPrice(totalPrice);

        Reservation savedReservation = reservationRepository.save(updatedReservation);
        return ReservationMapper.INSTANCE.updateResponseFromReservation(savedReservation);
    }

    @CachePut(cacheNames = "reservation_id", key = "'getReservationById' + #reservationId", unless = "#result == null")
    @Override
    public ChangeReservationStatusResponse changeReservationStatusById(Long reservationId, ReservationStatus status, String language) {
        Reservation foundReservation = findReservationById(reservationId, language);
        foundReservation.setStatus(status);

        Reservation savedReservation = reservationRepository.save(foundReservation);
        return ReservationMapper.INSTANCE.changeStatusResponseFromReservation(savedReservation);
    }

    @CachePut(cacheNames = "reservation_id", key = "'getReservationById' + #reservationId", unless = "#result == null")
    @Override
    public ChangeReservationStatusResponse confirmReservationById(Long reservationId, String language) throws MessagingException {
        Reservation foundReservation = findReservationById(reservationId, language);
        foundReservation.setStatus(ReservationStatus.CONFIRMED);

        Reservation savedReservation = reservationRepository.save(foundReservation);

        sendReservationConfirmEmailToUserAndManager(savedReservation, language);
        return ReservationMapper.INSTANCE.changeStatusResponseFromReservation(savedReservation);
    }

    @CachePut(cacheNames = "reservation_id", key = "'getReservationById' + #reservationId", unless = "#result == null")
    @Override
    public ChangeReservationStatusResponse cancelReservationById(Long reservationId, String language) throws MessagingException {
        Reservation foundReservation = findReservationById(reservationId, language);
        foundReservation.setStatus(ReservationStatus.CANCELLED);

        Reservation savedReservation = reservationRepository.save(foundReservation);

        sendReservationCancelledEmailToUserAndManager(savedReservation, language);
        return ReservationMapper.INSTANCE.changeStatusResponseFromReservation(savedReservation);
    }

    @CacheEvict(cacheNames = {"reservations","reservation_id","reservation_user_id","reservation_user_id","reservation_total_revenue_user_id", "past_reservation_user_id"}, allEntries = true)
    @Override
    public void deleteReservationById(Long reservationId, String language) {
        Reservation foundReservation = findReservationById(reservationId, language);
        reservationRepository.deleteById(foundReservation.getId());
    }

    @Override
    public Reservation findReservationById(Long reservationId, String language) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new BusinessException("error.reservationNotFound", language));
    }

    private void sendReservationCancelledEmailToUserAndManager(Reservation reservation, String language) throws MessagingException {
        ReservationCancelEmail toUser = EmailMapper.INSTANCE.reservationCancelEmailToUserFromReservation(reservation);
        ReservationCancelEmail toManager = EmailMapper.INSTANCE.reservationCancelEmailToManagerFromReservation(reservation);

        emailGateway.sendReservationCancellationEmail(toUser, language);
        emailGateway.sendReservationCancellationEmail(toManager, language);
    }

    private void sendReservationConfirmEmailToUserAndManager(Reservation reservation, String language) throws MessagingException {
        ReservationConfirmEmail toUser = EmailMapper.INSTANCE.reservationConfirmEmailToUserFromReservation(reservation);
        ReservationConfirmEmail toManager = EmailMapper.INSTANCE.reservationConfirmEmailToManagerFromReservation(reservation);

        emailGateway.sendReservationConfirmationEmail(toUser, language);
        emailGateway.sendReservationConfirmationEmail(toManager, language);
    }

    private BigDecimal calculateTotalPrice(LocalDate checkIn, LocalDate checkOut, Room foundRoom, String language) {
        checkDates(checkIn, checkOut, language);
        BigDecimal price = foundRoom.getPrice();
        long daysBetween = ChronoUnit.DAYS.between(checkIn, checkOut);
        BigDecimal daysBetweenBigDecimal = BigDecimal.valueOf(daysBetween);
        BigDecimal totalPrice;
        if (daysBetween > 0)
            totalPrice = daysBetweenBigDecimal.multiply(price);
        else
            totalPrice = price;
        return totalPrice;
    }

    private void checkDates(LocalDate checkIn, LocalDate checkOut, String language) {
        if (checkOut.isBefore(checkIn))
            throw new BusinessException("error.runtime", language);
    }
}
