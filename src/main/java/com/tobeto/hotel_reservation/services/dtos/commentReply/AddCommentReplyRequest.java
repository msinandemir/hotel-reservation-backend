package com.tobeto.hotel_reservation.services.dtos.commentReply;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentReplyRequest {
    @NotNull(message = "validation.notNull")
    @Size(min = 3, max = 255, message = "validation.size")
    private String content;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long userId;

    @NotNull(message = "validation.notNull")
    @Positive(message = "validation.positive")
    private Long commentId;
}
