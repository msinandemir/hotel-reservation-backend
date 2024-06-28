package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.CommentReply;
import com.tobeto.hotel_reservation.services.dtos.commentReply.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentReplyMapper {
    CommentReplyMapper INSTANCE = Mappers.getMapper(CommentReplyMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "comment.id", target = "commentId")
    GetCommentReplyResponse getResponseFromCommentReply(CommentReply commentReply);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "comment.id", source = "commentId")
    CommentReply commentReplyFromAddRequest(AddCommentReplyRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "comment.id", target = "commentId")
    AddCommentReplyResponse addResponseFromCommentReply(CommentReply commentReply);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "comment.id", source = "commentId")
    CommentReply commentReplyFromUpdateRequest(UpdateCommentReplyRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "comment.id", target = "commentId")
    UpdateCommentReplyResponse updateResponseFromCommentReply(CommentReply commentReply);
}
