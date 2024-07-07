package com.tobeto.hotel_reservation.services.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
    public String saveImage(MultipartFile file, String language) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url").toString();
    }

    @Override
    public void deleteImageByUrl(String url, String language) throws IOException {
        String publicId = getPublicIdFromImageUrl(url);
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    private String getPublicIdFromImageUrl(String url) {
        String[] splitUrl = url.split("/");
        String fileName = splitUrl[splitUrl.length - 1];
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
