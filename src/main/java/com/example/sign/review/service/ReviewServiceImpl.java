package com.example.sign.review.service;

import com.example.sign.escalate.Reviewer;
import com.example.sign.escalate.Reviews;
import com.example.sign.step.service.ReviewStepService;
import com.example.sign.ui.submit.Submit;
import com.example.sign.ui.result.Result;
import com.example.sign.review.entity.Review;
import com.example.sign.review.repository.ReviewRepository;
import com.example.sign.step.entity.ReviewStep;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final ReviewStepService stepService;

    @Override
    public Review findOne(long signId) {
        Review review = repository.findBySignId(signId);
        Set<ReviewStep> line = stepService.findByReview(review.getId());
        review.setLine(line);
        return review;
    }

    @Override
    public void request(Reviews reviews) {
        Review review = reviews.toEntity();
        if (review.isEmpty()) {
            return;
        }

        repository.create(review);

        long reviewId = review.getId();

        Set<Reviewer> added = reviews
                .getReviewers()
                .stream()
                .map(reviewer -> reviewer.addReviewId(reviewId))
                .collect(Collectors.toSet());
        stepService.assign(added);
    }

    @Override
    public Result review(Submit submit) {
        long signId = submit.getSignId();
        Review review = findOne(signId);
        review.review(submit);

        Set<ReviewStep> updated = review.getUpdated();
        stepService.update(updated);

        if (review.isFinish()) {
            repository.update(review);
        }

        return Result.fromReview(review.getStatus());
    }

    @Override
    public Result reject(Submit submit) {
        long signId = submit.getSignId();
        Review review = findOne(signId);
        review.reject(submit);

        Set<ReviewStep> updated = review.getUpdated();
        stepService.update(updated);

        if (review.isRejected()) {
            stepService.update(updated);
        }

        return Result.fromReview(review.getStatus());
    }
}
