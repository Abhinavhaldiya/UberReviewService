package com.example.uberreviewservice.services;

import com.example.uberreviewservice.DTOs.CreateReviewDto;
import com.example.uberreviewservice.DTOs.ReviewDto;
import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Optional<ReviewDto> getReviewById(Long reviewId);
    List<ReviewDto> getAllReviews();
    ReviewDto createReview(CreateReviewDto createReviewDto);
    ReviewDto updateReview(ReviewDto reviewDto, Long reviewId);
    void deleteReview(Long reviewId);
}
