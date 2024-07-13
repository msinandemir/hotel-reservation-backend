package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    EntityWithPagination getAllPhotosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy);

    GetPhotoResponse getPhotoById(Long photoId, String language);

    List<GetPhotoResponse> getPhotosByHotelId(Long hotelId, String language);

    AddPhotoResponse addPhoto(AddPhotoRequest request, MultipartFile file, String language) throws IOException;

    UpdatePhotoResponse updatePhotoById(Long photoId, UpdatePhotoRequest request, String language);

    void deletePhotoById(Long photoId, String language) throws IOException;
}
