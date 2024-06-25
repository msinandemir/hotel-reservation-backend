package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Comment;
import com.tobeto.hotel_reservation.services.dtos.comment.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "user.id", target = "userId")
    GetCommentResponse getResponseFromComment(Comment comment);

    @Mapping(target = "user.id", source = "userId")
    Comment commentFromAddRequest(AddCommentRequest request);

    @Mapping(source = "user.id", target = "userId")
    AddCommentResponse addResponseFromComment(Comment comment);

    @Mapping(target = "user.id", source = "userId")
    Comment commentFromUpdateRequest(UpdateCommentRequest request);

    @Mapping(source = "user.id", target = "userId")
    UpdateCommentResponse updateResponseFromComment(Comment comment);
}
