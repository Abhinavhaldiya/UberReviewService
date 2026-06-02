package com.example.uberreviewservice.adapters;

import com.example.uberentityservice.models.Review;
import com.example.uberreviewservice.DTOs.CreateReviewDto;
import com.example.uberreviewservice.DTOs.ReviewDto;


public interface CreateReviewDtoToReviewAdapter {
    Review toReview(CreateReviewDto createReviewDto);
    ReviewDto toReviewDto(Review review);
}
