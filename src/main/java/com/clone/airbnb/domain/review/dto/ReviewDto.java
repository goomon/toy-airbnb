package com.clone.airbnb.domain.review.dto;

import com.clone.airbnb.domain.review.model.Review;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class ReviewDto {

    private Long id;

    private Long user;

    private Long room;

    private Long experience;

    private String payload;

    private Integer rating;

    public ReviewDto(Review review) {
        id = review.getId();
        user = review.getUser().getId();
        if (review.getRoom() != null) {
            room = review.getRoom().getId();
        }
        if (review.getExperience() != null) {
            experience = review.getExperience().getId();
        }
        payload = review.getPayload();
        rating = review.getRating();
    }
}
