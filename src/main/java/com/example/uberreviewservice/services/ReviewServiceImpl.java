package com.example.uberreviewservice.services;

import com.example.uberentityservice.models.Review;
import com.example.uberreviewservice.DTOs.CreateReviewDto;
import com.example.uberreviewservice.DTOs.ReviewDto;
import com.example.uberreviewservice.adapters.CreateReviewDtoToReviewAdapterImpl;
import com.example.uberreviewservice.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CreateReviewDtoToReviewAdapterImpl reviewAdapter;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CreateReviewDtoToReviewAdapterImpl reviewAdapter) {
        this.reviewRepository = reviewRepository;
        this.reviewAdapter = reviewAdapter;
    }

    @Override
    public Optional<ReviewDto> getReviewById(Long reviewId) {
        try {
            return reviewRepository.findById(reviewId)
                    .map(reviewAdapter::toReviewDto);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching review with id: " + reviewId, e);
        }
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewAdapter::toReviewDto)
                .toList();
    }

    @Override
    public ReviewDto createReview(CreateReviewDto createReviewDto) {
        Review review = reviewAdapter.toReview(createReviewDto);
        return reviewAdapter.toReviewDto(reviewRepository.save(review));
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto, Long reviewId) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));
        existingReview.setRating(reviewDto.getRating());
        existingReview.setComment(reviewDto.getComment());
        return reviewAdapter.toReviewDto(reviewRepository.save(existingReview));
    }

    @Override
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new RuntimeException("Review not found with id: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }
}
