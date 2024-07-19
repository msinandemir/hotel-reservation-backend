package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.CommentReplyService;
import com.tobeto.hotel_reservation.services.dtos.commentReply.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/commentReplies")
@RequiredArgsConstructor
@Validated
public class CommentReplyController {
    private final CommentReplyService commentReplyService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getAllCommentRepliesWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                            @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                            @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(commentReplyService.getAllCommentRepliesWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{commentReplyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<GetCommentReplyResponse> getCommentReplyById(@PathVariable @Valid @Positive(message = "validation.positive") Long commentReplyId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentReplyService.getCommentReplyById(commentReplyId, lang));
    }

    @GetMapping("commentRepliesByUserId/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<EntityWithPagination> getCommentRepliesByUserIdWithPagination(@PathVariable @Valid @Positive(message = "validation.positive") Long userId,
                                                                                 @RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                                 @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                                 @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                                 @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(commentReplyService.getCommentRepliesByUserIdWithPagination(userId, pageNumber, pageSize, direction, sortBy));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<AddCommentReplyResponse> addCommentReply(@RequestBody @Valid AddCommentReplyRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(commentReplyService.addCommentReply(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{commentReplyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    ResponseEntity<UpdateCommentReplyResponse> updateCommentReplyById(@PathVariable @Valid @Positive(message = "validation.positive") Long commentReplyId, @RequestBody @Valid UpdateCommentReplyRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentReplyService.updateCommentReplyById(commentReplyId, request, lang));
    }

    @DeleteMapping("{commentReplyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
    void deleteCommentReplyById(@PathVariable @Valid @Positive(message = "validation.positive") Long commentReplyId, @RequestHeader(defaultValue = "en") String lang) {
        commentReplyService.deleteCommentReplyById(commentReplyId, lang);
    }
}
