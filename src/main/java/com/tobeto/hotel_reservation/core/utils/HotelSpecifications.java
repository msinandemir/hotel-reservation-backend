package com.tobeto.hotel_reservation.core.utils;

import com.tobeto.hotel_reservation.entities.concretes.Address;
import com.tobeto.hotel_reservation.entities.concretes.City;
import com.tobeto.hotel_reservation.entities.concretes.Hotel;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public final class HotelSpecifications {
    private HotelSpecifications() {
    }

    public static Specification<Hotel> hasStar(Integer star) {
        return (root, query, criteriaBuilder) ->
                star == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("star"), star);
    }

    public static Specification<Hotel> hasCityName(String cityName) {
        return (root, query, criteriaBuilder) -> {
            if (cityName == null) {
                return criteriaBuilder.conjunction();
            }
            Join<Hotel, Address> addressJoin = root.join("address");
            Join<Address, City> cityJoin = addressJoin.join("city");
            return criteriaBuilder.equal(cityJoin.get("name"), cityName);
        };
    }

}
