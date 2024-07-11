package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.abstracts.CommentReplyService;
import com.tobeto.hotel_reservation.services.dtos.commentReply.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/commentReplies")
@RequiredArgsConstructor
@Validated
public class CommentReplyController {
    private final CommentReplyService commentReplyService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllCommentRepliesWithPagination(@RequestBody @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(commentReplyService.getAllCommentRepliesWithPagination(paginationRequest));
    }

    @GetMapping("{commentReplyId}")
    ResponseEntity<GetCommentReplyResponse> getCommentReplyById(@PathVariable @Valid @Positive(message = "validation.positive") Long commentReplyId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentReplyService.getCommentReplyById(commentReplyId, lang));
    }

    @GetMapping("commentRepliesByUserId/{userId}")
    ResponseEntity<EntityWithPagination> getCommentRepliesByUserIdWithPagination(@PathVariable @Valid @Positive(message = "validation.positive") Long userId, @RequestBody @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(commentReplyService.getCommentRepliesByUserIdWithPagination(userId, paginationRequest));
    }

    @PostMapping
    ResponseEntity<AddCommentReplyResponse> addCommentReply(@RequestBody @Valid AddCommentReplyRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(commentReplyService.addCommentReply(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{commentReplyId}")
    ResponseEntity<UpdateCommentReplyResponse> updateCommentReplyById(@PathVariable @Valid @Positive(message = "validation.positive") Long commentReplyId, @RequestBody @Valid UpdateCommentReplyRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentReplyService.updateCommentReplyById(commentReplyId, request, lang));
    }

    @DeleteMapping("{commentReplyId}")
    void deleteCommentReplyById(@PathVariable @Valid @Positive(message = "validation.positive") Long commentReplyId, @RequestHeader(defaultValue = "en") String lang) {
        commentReplyService.deleteCommentReplyById(commentReplyId, lang);
    }
}
