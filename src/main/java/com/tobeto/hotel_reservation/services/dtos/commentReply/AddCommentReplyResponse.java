package com.tobeto.hotel_reservation.services.dtos.commentReply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentReplyResponse {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private String content;
    private Long userId;
    private Long commentId;
}
