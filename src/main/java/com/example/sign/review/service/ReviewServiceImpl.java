package com.example.sign.review.service;

import com.example.sign.step.entity.ProcessStep;
import com.example.sign.submit.Submit;
import com.example.sign.result.Result;
import com.example.sign.review.entity.Review;
import com.example.sign.review.repository.ReviewRepository;
import com.example.sign.step.entity.ReviewStep;
import com.example.sign.step.service.StepService;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final StepService stepService;

    @Override
    public Review findOne(long signId) {
        Review review = repository.findBySignId(signId);
        Set<ReviewStep> line = stepService.findByReview(review.getId());
        review.setLine(line);
        return review;
    }

    @Override
    public void escalate(Review review) {
        if (review.isEmpty()) {
            return;
        }

        repository.create(review);

        review.escalateLine();
        stepService.create(review.getLine());
    }

    @Override
    public Result review(Submit submit) {
        long signId = submit.getSignId();
        Review review = findOne(signId);
        review.review(submit);

        Set<ProcessStep> updated = review.getUpdated();
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

        Set<ProcessStep> updated = review.getUpdated();
        stepService.update(updated);

        if (review.isRejected()) {
            stepService.update(updated);
        }

        return Result.fromReview(review.getStatus());
    }
}
