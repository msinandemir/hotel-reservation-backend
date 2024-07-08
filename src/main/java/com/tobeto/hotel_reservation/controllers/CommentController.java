package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.CommentService;
import com.tobeto.hotel_reservation.services.dtos.comment.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllCommentsWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(commentService.getAllCommentsWithPagination(pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("{commentId}")
    ResponseEntity<GetCommentResponse> getCommentById(@PathVariable Long commentId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentService.getCommentById(commentId, lang));
    }

    @GetMapping("commentsByUserId/{userId}")
    ResponseEntity<EntityWithPagination> getCommentsByUserIdWithPagination(@PathVariable Long userId, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(commentService.getCommentsByUserIdWithPagination(userId, pageNumber, pageSize, Sort.Direction.DESC));
    }

    @GetMapping("commentsByHotelId/{hotelId}")
    ResponseEntity<EntityWithPagination> getCommentsByHotelIdWithPagination(@PathVariable Long hotelId, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.ok(commentService.getCommentsByHotelIdWithPagination(hotelId, pageNumber, pageSize, Sort.Direction.DESC));
    }

    @PostMapping
    ResponseEntity<AddCommentResponse> addComment(@RequestBody AddCommentRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(commentService.addComment(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{commentId}")
    ResponseEntity<UpdateCommentResponse> updateCommentById(@PathVariable Long commentId, @RequestBody UpdateCommentRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentService.updateCommentById(commentId, request, lang));
    }

    @DeleteMapping("{commentId}")
    void deleteCommentById(@PathVariable Long commentId, @RequestHeader(defaultValue = "en") String lang) {
        commentService.deleteCommentById(commentId, lang);
    }
}
