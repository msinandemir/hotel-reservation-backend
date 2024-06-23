package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.commentReply.*;
import org.springframework.data.domain.Sort;

public interface CommentReplyService {
    EntityWithPagination getAllUsersWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetCommentReplyResponse getCommentById(Long commentReplyId, String language);

    AddCommentReplyResponse addComment(AddCommentReplyRequest request);

    UpdateCommentReplyResponse updateCommentById(Long commentReplyId, UpdateCommentReplyRequest request, String language);

    void deleteCommentById(Long commentReplyId, String language);
}
