package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.entities.concretes.Comment;
import com.tobeto.hotel_reservation.services.dtos.comment.*;

public interface CommentService {
    EntityWithPagination getAllCommentsWithPagination(PaginationRequest paginationRequest);

    GetCommentResponse getCommentById(Long commentId, String language);

    EntityWithPagination getCommentsByUserIdWithPagination(Long userId, PaginationRequest paginationRequest);

    EntityWithPagination getCommentsByHotelIdWithPagination(Long hotelId, PaginationRequest paginationRequest);

    AddCommentResponse addComment(AddCommentRequest request, String language);

    UpdateCommentResponse updateCommentById(Long commentId, UpdateCommentRequest request, String language);

    void deleteCommentById(Long commentId, String language);

    Comment findCommentById(Long commentId, String language);
}
