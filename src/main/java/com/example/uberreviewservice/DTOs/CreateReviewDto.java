package com.example.uberreviewservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewDto {
    private String comment;
    private Double rating;
    private Long bookingId;
}
