package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Comment;
import com.tobeto.hotel_reservation.services.dtos.comment.*;
import org.springframework.data.domain.Sort;

public interface CommentService {
    EntityWithPagination getAllCommentsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy);

    GetCommentResponse getCommentById(Long commentId, String language);

    EntityWithPagination getCommentsByUserIdWithPagination(Long userId, int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy);

    EntityWithPagination getCommentsByHotelIdWithPagination(Long hotelId, int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy);

    AddCommentResponse addComment(AddCommentRequest request, String language);

    UpdateCommentResponse updateCommentById(Long commentId, UpdateCommentRequest request, String language);

    void deleteCommentById(Long commentId, String language);

    Comment findCommentById(Long commentId, String language);
}
