package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.CommentService;
import com.tobeto.hotel_reservation.services.dtos.comment.*;
import org.springframework.data.domain.Sort;

public class CommentServiceImpl implements CommentService {
    @Override
    public EntityWithPagination getAllCommentsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetCommentResponse getCommentById(Long commentId, String language) {
        return null;
    }

    @Override
    public AddCommentResponse addComment(AddCommentRequest request) {
        return null;
    }

    @Override
    public UpdateCommentResponse updateCommentById(Long commentId, UpdateCommentRequest request, String language) {
        return null;
    }

    @Override
    public void deleteCommentById(Long commentId, String language) {

    }
}
