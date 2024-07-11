package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    EntityWithPagination getAllPhotosWithPagination(PaginationRequest paginationRequest);

    GetPhotoResponse getPhotoById(Long photoId, String language);

    AddPhotoResponse addPhoto(AddPhotoRequest request, MultipartFile file, String language) throws IOException;

    UpdatePhotoResponse updatePhotoById(Long photoId, UpdatePhotoRequest request, String language);

    void deletePhotoById(Long photoId, String language) throws IOException;
}
