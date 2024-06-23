package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.CommentReplyService;
import com.tobeto.hotel_reservation.services.dtos.commentReply.*;
import org.springframework.data.domain.Sort;

public class CommentReplyServiceImpl implements CommentReplyService {
    @Override
    public EntityWithPagination getAllCommentRepliesWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetCommentReplyResponse getCommentById(Long commentReplyId, String language) {
        return null;
    }

    @Override
    public AddCommentReplyResponse addComment(AddCommentReplyRequest request) {
        return null;
    }

    @Override
    public UpdateCommentReplyResponse updateCommentById(Long commentReplyId, UpdateCommentReplyRequest request, String language) {
        return null;
    }

    @Override
    public void deleteCommentById(Long commentReplyId, String language) {

    }
}
