package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.CommentReply;
import com.tobeto.hotel_reservation.repositories.CommentReplyRepository;
import com.tobeto.hotel_reservation.services.abstracts.CommentReplyService;
import com.tobeto.hotel_reservation.services.abstracts.CommentService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.commentReply.*;
import com.tobeto.hotel_reservation.services.mappers.CommentReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentReplyServiceImpl implements CommentReplyService {
    private final CommentReplyRepository commentReplyRepository;
    private final CommentService commentService;
    private final UserService userService;

    @Cacheable(cacheNames = "comment_replies", key = "#root.methodName + #pageNumber + '_' + #pageSize", unless = "#result == null")
    @Override
    public EntityWithPagination getAllCommentRepliesWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        Sort sorting = Sort.by(sortDirection, "createdAt");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<CommentReply> commentReplies = commentReplyRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(commentReplies);

        List<GetCommentReplyResponse> responses = commentReplies.stream()
                .map(CommentReplyMapper.INSTANCE::getResponseFromCommentReply)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "comment_reply_id", key = "#root.methodName + #commentReplyId", unless = "#result == null")
    @Override
    public GetCommentReplyResponse getCommentReplyById(Long commentReplyId, String language) {
        CommentReply foundCommentReply = findCommentReplyById(commentReplyId, language);
        return CommentReplyMapper.INSTANCE.getResponseFromCommentReply(foundCommentReply);
    }

    @CacheEvict(cacheNames = {"comment_replies", "comment_reply_id"}, allEntries = true)
    @Override
    public AddCommentReplyResponse addCommentReply(AddCommentReplyRequest request, String language) {
        commentService.findCommentById(request.getCommentId(), language);
        userService.findUserById(request.getUserId(), language);
        CommentReply commentReply = CommentReplyMapper.INSTANCE.commentReplyFromAddRequest(request);
        CommentReply savedCommentReply = commentReplyRepository.save(commentReply);
        return CommentReplyMapper.INSTANCE.addResponseFromCommentReply(savedCommentReply);
    }

    @CachePut(cacheNames = "comment_reply_id", key = "getCommentReplyById + #request.id", unless = "#result == null")
    @Override
    public UpdateCommentReplyResponse updateCommentReplyById(Long commentReplyId, UpdateCommentReplyRequest request, String language) {
        commentService.findCommentById(request.getCommentId(), language);
        userService.findUserById(request.getUserId(), language);
        CommentReply foundCommentReply = findCommentReplyById(commentReplyId, language);
        CommentReply updatedCommentReply = CommentReplyMapper.INSTANCE.commentReplyFromUpdateRequest(request);
        updatedCommentReply.setId(foundCommentReply.getId());

        CommentReply savedCommentReply = commentReplyRepository.save(updatedCommentReply);
        return CommentReplyMapper.INSTANCE.updateResponseFromCommentReply(savedCommentReply);
    }

    @CacheEvict(cacheNames = {"comment_replies", "comment_reply_id"}, allEntries = true)
    @Override
    public void deleteCommentReplyById(Long commentReplyId, String language) {
        CommentReply foundCommentReply = findCommentReplyById(commentReplyId, language);
        commentReplyRepository.deleteById(foundCommentReply.getId());
    }

    private CommentReply findCommentReplyById(Long commentReplyId, String language) {
        return commentReplyRepository.findById(commentReplyId).orElseThrow(() -> new BusinessException("error.commentReplyNotFound", language));
    }
}
