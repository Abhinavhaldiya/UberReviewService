# UberReviewService

## Overview
**UberReviewService** is a Spring Boot microservice designed to manage user and driver reviews within an Uber-like ecosystem. It provides a dedicated service layer to handle all Create, Read, Update, and Delete (CRUD) operations for reviews, ensuring clean separation of concerns and secure data handling using Data Transfer Objects (DTOs).

## Features
* **Create Reviews**: Submit new reviews into the system safely using `CreateReviewDto`.
* **Retrieve Reviews**: Fetch a comprehensive list of all reviews or query a specific review by its unique ID.
* **Update Reviews**: Modify existing reviews, specifically allowing updates to ratings and comments.
* **Delete Reviews**: Safely remove a review from the system.
* **DTO Mapping**: Utilizes an adapter pattern (`CreateReviewDtoToReviewAdapterImpl`) to seamlessly map data between external API DTOs and internal database entities.

## Tech Stack
* **Language:** Java
* **Framework:** Spring Boot
* **Design Patterns:** Dependency Injection, Adapter Pattern, DTO Pattern
* **Dependencies:** Integrates with models from `UberEntityService`.

## Core Architecture

### `ReviewServiceImpl`
The core business logic is encapsulated in the `ReviewServiceImpl` class, which implements the `ReviewService` interface. It leverages Spring's `@Service` annotation and relies on constructor-based dependency injection for its components:

* `ReviewRepository`: Handles direct database interactions for the `Review` entity.
* `CreateReviewDtoToReviewAdapterImpl`: Handles data transformations between `Review` entities and `ReviewDto`s.

#### Core Service Methods:
* `getReviewById(Long reviewId)`: Retrieves a specific review and returns it as an `Optional<ReviewDto>`.
* `getAllReviews()`: Retrieves all system reviews as a `List<ReviewDto>`.
* `createReview(CreateReviewDto createReviewDto)`: Transforms the incoming DTO into an entity, persists it, and returns the saved `ReviewDto`.
* `updateReview(ReviewDto reviewDto, Long reviewId)`: Finds an existing review, updates its rating and comment, and saves the changes.
* `deleteReview(Long reviewId)`: Verifies the existence of a review and deletes it by ID.
