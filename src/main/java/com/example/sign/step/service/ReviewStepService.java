package com.example.sign.step.service;

import com.example.sign.escalate.Reviewer;
import com.example.sign.step.entity.ReviewStep;

import java.util.Collection;
import java.util.Set;

public interface ReviewStepService {

    Set<ReviewStep> findByReview(long reviewId);

    void assign(Collection<Reviewer> reviewers);

    void update(ReviewStep step);

    void update(Collection<ReviewStep> steps);

}
