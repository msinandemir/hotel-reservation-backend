package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.PhotoService;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import org.springframework.data.domain.Sort;

public class PhotoServiceImpl implements PhotoService {
    @Override
    public EntityWithPagination getAllPhotosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetPhotoResponse getPhotoById(Long photoId, String language) {
        return null;
    }

    @Override
    public AddPhotoResponse addPhoto(AddPhotoRequest request) {
        return null;
    }

    @Override
    public UpdatePhotoResponse updatePhotoById(Long photoId, UpdatePhotoRequest request, String language) {
        return null;
    }

    @Override
    public void deletePhotoById(Long photoId, String language) {

    }
}
