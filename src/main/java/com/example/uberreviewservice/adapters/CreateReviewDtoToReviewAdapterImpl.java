package com.example.uberreviewservice.adapters;

import com.example.uberentityservice.models.Booking;
import com.example.uberentityservice.models.Review;
import com.example.uberreviewservice.DTOs.CreateReviewDto;
import com.example.uberreviewservice.DTOs.ReviewDto;
import com.example.uberreviewservice.repositories.BookingRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateReviewDtoToReviewAdapterImpl implements CreateReviewDtoToReviewAdapter {

    private final BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review toReview(CreateReviewDto createReviewDto) {
        Booking booking = bookingRepository.findById(createReviewDto.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + createReviewDto.getBookingId()));

        return Review.builder()
                .comment(createReviewDto.getComment())
                .rating(createReviewDto.getRating())
                .booking(booking)
                .build();
    }

    @Override
    public ReviewDto toReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setComment(review.getComment());
        reviewDto.setRating(review.getRating());
        reviewDto.setBookingId(review.getBooking().getId());
        reviewDto.setCreatedAt(review.getCreatedAt());
        reviewDto.setUpdatedAt(review.getUpdatedAt());
        return reviewDto;
    }
}
