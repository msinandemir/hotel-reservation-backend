package com.tobeto.hotel_reservation.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntityWithPagination {
    private long totalElements;
    private long totalPages;
    private long size;
    private List<?> content;
    private long number;
    private boolean first;
    private boolean last;

    public void mappedFromPageWithoutContent(Page<?> pagedEntity) {
        this.setTotalElements(pagedEntity.getTotalElements());
        this.setTotalPages(pagedEntity.getTotalPages());
        this.setSize(pagedEntity.getSize());
        this.setNumber(pagedEntity.getNumber());
        this.setFirst(pagedEntity.isFirst());
        this.setLast(pagedEntity.isLast());
    }
}
