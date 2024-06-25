package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.Comment;
import com.tobeto.hotel_reservation.repositories.CommentRepository;
import com.tobeto.hotel_reservation.services.abstracts.CommentService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.comment.*;
import com.tobeto.hotel_reservation.services.mappers.CommentMapper;
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
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Cacheable(cacheNames = "comments", key = "#root.methodName + #pageNumber + '_' + #pageSize", unless = "#result == null")
    @Override
    public EntityWithPagination getAllCommentsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        Sort sorting = Sort.by(sortDirection, "createdAt");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<Comment> comments = commentRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(comments);

        List<GetCommentResponse> responses = comments.stream()
                .map(CommentMapper.INSTANCE::getResponseFromComment)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "comment_id", key = "#root.methodName + #commentId", unless = "#result == null")
    @Override
    public GetCommentResponse getCommentById(Long commentId, String language) {
        Comment foundComment = findCommentById(commentId, language);
        return CommentMapper.INSTANCE.getResponseFromComment(foundComment);
    }

    @CacheEvict(cacheNames = {"comments", "comment_id"}, allEntries = true)
    @Override
    public AddCommentResponse addComment(AddCommentRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        Comment comment = CommentMapper.INSTANCE.commentFromAddRequest(request);
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.INSTANCE.addResponseFromComment(savedComment);
    }

    @CachePut(cacheNames = "comment_id", key = "getCommentById + #request.id", unless = "#result == null")
    @Override
    public UpdateCommentResponse updateCommentById(Long commentId, UpdateCommentRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        Comment foundComment = findCommentById(commentId, language);
        Comment updatedComment = CommentMapper.INSTANCE.commentFromUpdateRequest(request);
        updatedComment.setId(foundComment.getId());

        Comment savedComment = commentRepository.save(updatedComment);
        return CommentMapper.INSTANCE.updateResponseFromComment(savedComment);
    }

    @CacheEvict(cacheNames = {"comments", "comment_id"}, allEntries = true)
    @Override
    public void deleteCommentById(Long commentId, String language) {
        Comment foundComment = findCommentById(commentId, language);
        commentRepository.deleteById(foundComment.getId());
    }

    @Override
    public Comment findCommentById(Long commentId, String language) {
        return commentRepository.findById(commentId).orElseThrow(() -> new BusinessException("error.commentNotFound", language));
    }
}
