package com.tobeto.hotel_reservation.controllers;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.CommentService;
import com.tobeto.hotel_reservation.services.dtos.comment.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
@Validated
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    ResponseEntity<EntityWithPagination> getAllCommentsWithPagination(@RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                      @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                      @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                      @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(commentService.getAllCommentsWithPagination(pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("{commentId}")
    ResponseEntity<GetCommentResponse> getCommentById(@PathVariable @Valid @Positive(message = "validation.positive") Long commentId, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentService.getCommentById(commentId, lang));
    }

    @GetMapping("commentsByUserId/{userId}")
    ResponseEntity<EntityWithPagination> getCommentsByUserIdWithPagination(@PathVariable @Valid @Positive(message = "validation.positive") Long userId,
                                                                           @RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                           @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                           @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                           @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(commentService.getCommentsByUserIdWithPagination(userId, pageNumber, pageSize, direction, sortBy));
    }

    @GetMapping("commentsByHotelId/{hotelId}")
    ResponseEntity<EntityWithPagination> getCommentsByHotelIdWithPagination(@PathVariable @Valid @Positive(message = "validation.positive") Long hotelId,
                                                                            @RequestParam(defaultValue = "0") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageNumber,
                                                                            @RequestParam(defaultValue = "16") @Valid @PositiveOrZero(message = "validation.positiveOrZero") int pageSize,
                                                                            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
                                                                            @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(commentService.getCommentsByHotelIdWithPagination(hotelId, pageNumber, pageSize, direction, sortBy));
    }

    @PostMapping
    ResponseEntity<AddCommentResponse> addComment(@RequestBody @Valid AddCommentRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return new ResponseEntity<>(commentService.addComment(request, lang), HttpStatus.CREATED);
    }

    @PutMapping("{commentId}")
    ResponseEntity<UpdateCommentResponse> updateCommentById(@PathVariable @Valid @Positive(message = "validation.positive") Long commentId, @RequestBody @Valid UpdateCommentRequest request, @RequestHeader(defaultValue = "en") String lang) {
        return ResponseEntity.ok(commentService.updateCommentById(commentId, request, lang));
    }

    @DeleteMapping("{commentId}")
    void deleteCommentById(@PathVariable @Valid @Positive(message = "validation.positive") Long commentId, @RequestHeader(defaultValue = "en") String lang) {
        commentService.deleteCommentById(commentId, lang);
    }
}
