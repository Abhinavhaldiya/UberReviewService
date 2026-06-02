package com.example.uberreviewservice.models;

import com.example.uberentityservice.models.Review;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "passenger_review")
@Getter
@Setter
@NoArgsConstructor
public class PassengerReview extends Review {
}
