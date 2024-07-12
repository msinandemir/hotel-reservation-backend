package com.tobeto.hotel_reservation.core.utils;

import com.tobeto.hotel_reservation.entities.concretes.Hotel;
import com.tobeto.hotel_reservation.entities.concretes.Room;
import com.tobeto.hotel_reservation.entities.enums.RoomType;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class RoomSpecifications {
    private RoomSpecifications() {
    }

    public static Specification<Room> hasCapacity(Integer capacity) {
        return (root, query, criteriaBuilder) ->
                capacity == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("capacity"), capacity);
    }

    public static Specification<Room> hasPrice(BigDecimal price) {
        return (root, query, criteriaBuilder) ->
                price == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("price"), price);
    }

    public static Specification<Room> isAvailable(Boolean availability) {
        return (root, query, criteriaBuilder) ->
                availability == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("availability"), availability);
    }

    public static Specification<Room> hasSingleBed(Integer singleBed) {
        return (root, query, criteriaBuilder) ->
                singleBed == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("singleBed"), singleBed);
    }

    public static Specification<Room> hasDoubleBed(Integer doubleBed) {
        return (root, query, criteriaBuilder) ->
                doubleBed == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("doubleBed"), doubleBed);
    }

    public static Specification<Room> hasBunkBed(Integer bunkBed) {
        return (root, query, criteriaBuilder) ->
                bunkBed == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("bunkBed"), bunkBed);
    }

    public static Specification<Room> hasType(RoomType type) {
        return (root, query, criteriaBuilder) ->
                type == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<Room> hasHotelId(Long hotelId) {
        return ((root, query, criteriaBuilder) -> {
            if (hotelId == null)
                return criteriaBuilder.conjunction();

            Join<Room, Hotel> hotelJoin = root.join("hotel");
            return criteriaBuilder.equal(hotelJoin.get("id"), hotelId);
        });

    }
}
