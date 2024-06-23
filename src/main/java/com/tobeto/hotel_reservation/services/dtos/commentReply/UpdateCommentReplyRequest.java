package com.tobeto.hotel_reservation.services.dtos.commentReply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentReplyRequest {
    private String content;
    private Long userId;
    private Long commentId;
}
