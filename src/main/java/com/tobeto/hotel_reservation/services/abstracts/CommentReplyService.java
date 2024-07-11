package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.dtos.commentReply.*;

public interface CommentReplyService {
    EntityWithPagination getAllCommentRepliesWithPagination(PaginationRequest paginationRequest);

    GetCommentReplyResponse getCommentReplyById(Long commentReplyId, String language);

    EntityWithPagination getCommentRepliesByUserIdWithPagination(Long userId, PaginationRequest paginationRequest);

    AddCommentReplyResponse addCommentReply(AddCommentReplyRequest request, String language);

    UpdateCommentReplyResponse updateCommentReplyById(Long commentReplyId, UpdateCommentReplyRequest request, String language);

    void deleteCommentReplyById(Long commentReplyId, String language);
}
