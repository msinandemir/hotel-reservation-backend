package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.comment.*;
import org.springframework.data.domain.Sort;

public interface CommentService {
    EntityWithPagination getAllUsersWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetCommentResponse getCommentById(Long commentId, String language);

    AddCommentResponse addComment(AddCommentRequest request);

    UpdateCommentResponse updateCommentById(Long commentId, UpdateCommentRequest request, String language);

    void deleteCommentById(Long commentId, String language);
}
