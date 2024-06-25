package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.commentReply.*;
import org.springframework.data.domain.Sort;

public interface CommentReplyService {
    EntityWithPagination getAllCommentRepliesWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetCommentReplyResponse getCommentReplyById(Long commentReplyId, String language);

    AddCommentReplyResponse addCommentReply(AddCommentReplyRequest request, String language);

    UpdateCommentReplyResponse updateCommentReplyById(Long commentReplyId, UpdateCommentReplyRequest request, String language);

    void deleteCommentReplyById(Long commentReplyId, String language);
}
