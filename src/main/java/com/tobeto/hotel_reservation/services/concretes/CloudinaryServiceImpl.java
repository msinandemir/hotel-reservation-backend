package com.tobeto.hotel_reservation.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.services.abstracts.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Override
    public String saveImage(MultipartFile file, String language) {
        String imageUrl;
        try {
            imageUrl = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url").toString();
        } catch (RuntimeException | IOException e) {
            throw new BusinessException("image.uploadError", language);
        }

        return imageUrl;
    }

    @Override
    public void deleteImageByUrl(String url, String language) {
        String publicId = getPublicIdFromImageUrl(url);
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (RuntimeException | IOException e) {
            throw new BusinessException("image.deleteError", language);
        }
    }

    private String getPublicIdFromImageUrl(String url) {
        String[] splitUrl = url.split("/");
        String fileName = splitUrl[splitUrl.length - 1];
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
