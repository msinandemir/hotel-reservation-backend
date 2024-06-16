package com.tobeto.hotel_reservation.entities.concretes;

import com.tobeto.hotel_reservation.entities.abstracts.BaseComment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "comment_replies")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentReply extends BaseComment {
}
