package com.clone.airbnb.domain.review.controller;

import com.clone.airbnb.domain.review.dto.ReviewDto;
import com.clone.airbnb.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> findAll() {
        return reviewService.findAll().stream()
                .map(ReviewDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ReviewDto findById(@PathVariable("id") Long id) {
        return new ReviewDto(reviewService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody ReviewDto requestData) {
        reviewService.save(requestData);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        reviewService.deleteById(id);
    }
}
