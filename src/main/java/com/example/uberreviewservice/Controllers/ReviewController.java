package com.example.uberreviewservice.Controllers;

import com.example.uberreviewservice.DTOs.CreateReviewDto;
import com.example.uberreviewservice.DTOs.ReviewDto;
import com.example.uberreviewservice.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{reviewId}")
    public ReviewDto getReviewById(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody CreateReviewDto createReviewDto) {
        return reviewService.createReview(createReviewDto);
    }

    @PutMapping("/{reviewId}")
    public ReviewDto updateReview(@RequestBody ReviewDto reviewDto, @PathVariable Long reviewId) {
        return reviewService.updateReview(reviewDto, reviewId);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReviewById(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}

