package com.example.sign.escalate;

import com.example.sign.step.entity.ReviewStep;
import com.example.sign.step.entity.ReviewStepImpl;
import com.example.sign.review.entity.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class Reviews {

    private final Set<Reviewer> reviewers;

    public Reviews(Reviewer... reviewers) {
        this.reviewers = new HashSet<>(Arrays.asList(reviewers));
    }

    public Review toEntity(long signId) {
        if (isEmpty()) {
            return Review.empty();
        }

        return new Review(signId, makeLine());
    }

    public static Reviews empty() {
        return new Reviews(Collections.emptySet());
    }

    private Set<ReviewStep> makeLine() {
        return reviewers.stream().map(this::makeStep).collect(Collectors.toSet());
    }

    private ReviewStep makeStep(Reviewer reviewer) {
        return new ReviewStepImpl(reviewer.getId());
    }

    private boolean isEmpty() {
        return this.reviewers.isEmpty();
    }
}
