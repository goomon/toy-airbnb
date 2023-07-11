package com.clone.airbnb.domain.review.model;

import com.clone.airbnb.common.model.TimestampModel;
import com.clone.airbnb.domain.experience.model.Experience;
import com.clone.airbnb.domain.review.dto.ReviewDto;
import com.clone.airbnb.domain.room.model.Room;
import com.clone.airbnb.domain.users.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "REVIEW_ID"))
public class Review extends TimestampModel {

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "EXPERIENCE_ID")
    private Experience experience;

    @Column(length = 200)
    private String payload;

    @Column(nullable = false)
    private Integer rating;

    public static Review createReview(ReviewDto reviewDto, User user, Room room, Experience experience) {
        Review review = new Review();
        review.setUser(user);
        review.setRoom(room);
        review.setExperience(experience);
        review.setPayload(reviewDto.getPayload());
        review.setRating(reviewDto.getRating());
        return review;
    }
}
