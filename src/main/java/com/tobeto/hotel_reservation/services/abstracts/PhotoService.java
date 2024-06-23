package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import org.springframework.data.domain.Sort;

public interface PhotoService {
    EntityWithPagination getAllPhotosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetPhotoResponse getPhotoById(Long photoId, String language);

    AddPhotoResponse addPhoto(AddPhotoRequest request);

    UpdatePhotoResponse updatePhotoById(Long photoId, UpdatePhotoRequest request, String language);

    void deletePhotoById(Long photoId, String language);
}
