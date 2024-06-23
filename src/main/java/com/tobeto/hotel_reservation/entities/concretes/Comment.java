package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseComment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "comments")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseComment {
    @Column(nullable = false)
    private int cleanRating;

    @Column(nullable = false)
    private int locationRating;

    @Column(nullable = false)
    private int serviceRating;

    @Column(nullable = false)
    private int confortableRating;

    @Column(nullable = false)
    private int priceBalance;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private double overallRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<CommentReply> commentReplies;

    @PrePersist
    @PreUpdate
    private void calculate(){
        int totalRating = this.cleanRating + this.locationRating + this.serviceRating + this.confortableRating + this.priceBalance;
        this.overallRating = (double) totalRating / 5;
    }
}
