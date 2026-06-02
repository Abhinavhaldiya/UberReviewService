package com.example.uberreviewservice;

import com.example.uberentityservice.models.Booking;
import com.example.uberentityservice.models.Review;
import com.example.uberreviewservice.DTOs.ReviewDto;
import com.example.uberreviewservice.adapters.CreateReviewDtoToReviewAdapterImpl;
import com.example.uberreviewservice.repositories.ReviewRepository;
import com.example.uberreviewservice.services.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private CreateReviewDtoToReviewAdapterImpl reviewAdapter;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    public void testGetReviewById_success() {
        Booking booking = new Booking();
        booking.setId(1L);

        Review review = new Review();
        review.setId(1L);
        review.setRating(1.0);
        review.setBooking(booking);

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(1L);
        reviewDto.setRating(1.0);
        reviewDto.setBookingId(1L);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewAdapter.toReviewDto(review)).thenReturn(reviewDto);

        Optional<ReviewDto> result = reviewService.getReviewById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals(1.0, result.get().getRating());
        assertEquals(1L, result.get().getBookingId());
    }
}