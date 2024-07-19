package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Photo;
import com.tobeto.hotel_reservation.repositories.PhotoRepository;
import com.tobeto.hotel_reservation.services.abstracts.CloudinaryService;
import com.tobeto.hotel_reservation.services.abstracts.HotelService;
import com.tobeto.hotel_reservation.services.abstracts.PhotoService;
import com.tobeto.hotel_reservation.services.dtos.photo.*;
import com.tobeto.hotel_reservation.services.mappers.PhotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final CloudinaryService cloudinaryService;
    private final HotelService hotelService;

    @Override
    public EntityWithPagination getAllPhotosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy) {
        Sort sorting = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<Photo> photos = photoRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(photos);

        List<GetPhotoResponse> responses = photos.stream()
                .map(PhotoMapper.INSTANCE::getResponseFromPhoto)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "photo_id", key = "#root.methodName + #photoId", unless = "#result == nulll")
    @Override
    public GetPhotoResponse getPhotoById(Long photoId, String language) {
        Photo foundPhoto = findPhotoById(photoId, language);
        return PhotoMapper.INSTANCE.getResponseFromPhoto(foundPhoto);
    }

    @Cacheable(cacheNames = "photos_hotel_id", key = "#root.methodName + #hotelId", unless = "#result == null")
    @Override
    public List<GetPhotoResponse> getPhotosByHotelId(Long hotelId, String language) {
        hotelService.findHotelById(hotelId, language);
        List<Photo> getPhotos = photoRepository.findByHotelId(hotelId);
        return getPhotos.stream()
                .map(PhotoMapper.INSTANCE::getResponseFromPhoto)
                .collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = {"photo_id", "photos_hotel_id"}, allEntries = true)
    @Override
    public AddPhotoResponse addPhoto(AddPhotoRequest request, String language) throws IOException {
        hotelService.findHotelById(request.getHotelId(), language);
        String url = cloudinaryService.saveImage(request.getFile(), language);
        Photo photo = PhotoMapper.INSTANCE.photoFromAddRequest(request);
        photo.setUrl(url);

        Photo savedPhoto = photoRepository.save(photo);
        return PhotoMapper.INSTANCE.addResponseFromPhoto(savedPhoto);
    }

    @CachePut(cacheNames = "photo_id", key = "'getPhotoById' + #photoId", unless = "#result == null")
    @Override
    public UpdatePhotoResponse updatePhotoById(Long photoId, UpdatePhotoRequest request, String language) {
        hotelService.findHotelById(request.getHotelId(), language);
        Photo foundPhoto = findPhotoById(photoId, language);
        Photo updatedPhoto = PhotoMapper.INSTANCE.photoFromUpdateRequest(request);
        updatedPhoto.setId(foundPhoto.getId());

        Photo savedPhoto = photoRepository.save(updatedPhoto);
        return PhotoMapper.INSTANCE.updateResponseFromPhoto(savedPhoto);
    }

    @CacheEvict(cacheNames = {"photo_id", "photos_hotel_id"}, allEntries = true)
    @Override
    public void deletePhotoById(Long photoId, String language) throws IOException {
        Photo foundPhoto = findPhotoById(photoId, language);
        cloudinaryService.deleteImageByUrl(foundPhoto.getUrl(), language);
        photoRepository.deleteById(foundPhoto.getId());
    }

    private Photo findPhotoById(Long photoId, String language) {
        return photoRepository.findById(photoId).orElseThrow(() -> new BusinessException("error.photoNotFound", language));
    }
}
