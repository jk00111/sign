package com.example.sign.review.service;

import com.example.sign.event.RejectEvent;
import com.example.sign.event.ReviewEvent;
import com.example.sign.line.service.LineService;
import com.example.sign.review.entity.Review;
import com.example.sign.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final LineService lineService;


    @Override
    public Review findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public void escalate(Review review) {
        if (review == null) {
            return;
        }

        repository.create(review);
        lineService.create(review.getSteps());
    }

    @Override
    public void review(long reviewId, ReviewEvent event) {
        if (!event.isReviewed()) {
            return;
        }

        Review review = findOne(reviewId);
        review.review(event);
        repository.update(review);
    }

    @Override
    public void reject(long reviewId, RejectEvent event) {
        Review review = findOne(reviewId);
        review.reject(event);
        repository.update(review);
    }
}
