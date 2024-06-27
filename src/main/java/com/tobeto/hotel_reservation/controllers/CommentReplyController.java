package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.CommentReplyService;
import com.tobeto.hotel_reservation.services.dtos.commentReply.*;
import com.tobeto.hotel_reservation.services.dtos.userInfo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/commentReplies")
@RequiredArgsConstructor
public class CommentReplyController {
    private CommentReplyService commentReplyService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllCommentRepliesWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(commentReplyService.getAllCommentRepliesWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{commentReplyId}")
    ResponseEntity<GetCommentReplyResponse> getCommentReplyById(@PathVariable Long commentReplyId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentReplyService.getCommentReplyById(commentReplyId, lang));
    }

    @PostMapping
    ResponseEntity<AddCommentReplyResponse> addCommentReply(@RequestBody AddCommentReplyRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(commentReplyService.addCommentReply(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{commentReplyId}")
    ResponseEntity<UpdateCommentReplyResponse> updateCommentReplyById(@PathVariable Long commentReplyId, @RequestBody UpdateCommentReplyRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentReplyService.updateCommentReplyById(commentReplyId, request, lang));
    }

    @DeleteMapping("{commentReplyId}")
    void deleteCommentReplyById(@PathVariable Long commentReplyId, @RequestHeader(defaultValue = "en") String lang) {
        commentReplyService.deleteCommentReplyById(commentReplyId, lang);
    }
}
