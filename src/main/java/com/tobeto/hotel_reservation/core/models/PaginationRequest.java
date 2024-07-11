package com.tobeto.hotel_reservation.core.models;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {
    @PositiveOrZero(message = "validation.positiveOrZero")
    private int pageNumber;

    @PositiveOrZero(message = "validation.positiveOrZero")
    private int pageSize;
    private Sort.Direction sortDirection;
    private String sortBy;
}
