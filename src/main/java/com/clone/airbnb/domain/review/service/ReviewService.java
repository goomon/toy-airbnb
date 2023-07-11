package com.clone.airbnb.domain.review.service;

import com.clone.airbnb.domain.experience.model.Experience;
import com.clone.airbnb.domain.experience.repository.ExperienceRepository;
import com.clone.airbnb.domain.review.dto.ReviewDto;
import com.clone.airbnb.domain.review.model.Review;
import com.clone.airbnb.domain.review.repository.ReviewRepository;
import com.clone.airbnb.domain.room.model.Room;
import com.clone.airbnb.domain.room.repository.RoomRepository;
import com.clone.airbnb.domain.users.model.User;
import com.clone.airbnb.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ExperienceRepository experienceRepository;

    public void save(ReviewDto reviewDto) {
        User user = userRepository.findById(reviewDto.getId());
        Room room = roomRepository.findById(reviewDto.getRoom());
        Experience experience = experienceRepository.findById(reviewDto.getExperience());
        Review review = Review.createReview(reviewDto, user, room, experience);
        reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id);
    }

    public void deleteById(Long id) {
        Review review = reviewRepository.findById(id);
        reviewRepository.delete(review);
    }

    public List<Review> paginate(Integer limit, Integer offset) {
        return reviewRepository.paginate(limit, offset);
    }
}
