package com.tobeto.hotel_reservation.services.dtos.commentReply;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentReplyRequest {
    @NotNull(message = "validation.NotNull")
    private String content;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long userId;
    @NotNull(message = "validation.NotNull")
    @Positive
    private Long commentId;
}
