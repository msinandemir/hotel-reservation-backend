package com.tobeto.hotel_reservation.services.mappers;

import com.tobeto.hotel_reservation.entities.concretes.Comment;
import com.tobeto.hotel_reservation.services.dtos.comment.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    GetCommentResponse getResponseFromComment(Comment comment);
    Comment commentFromAddRequest(AddCommentRequest request);
    AddCommentResponse addResponseFromComment(Comment comment);
    Comment commentFromUpdateRequest(UpdateCommentRequest request);
    UpdateCommentResponse updateResponseFromComment(Comment comment);


}
