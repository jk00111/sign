package iit.sign.review.service;

import iit.sign.api.command.Reviewer;
import iit.sign.api.command.Reviews;
import iit.sign.api.command.Submit;
import iit.sign.step.service.ReviewStepService;
import iit.sign.common.ProcessResult;
import iit.sign.review.entity.Review;
import iit.sign.review.repository.ReviewRepository;
import iit.sign.step.entity.ReviewStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
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
    public void escalate(Reviews reviews) {
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
    public ProcessResult review(Submit submit) {
        long signId = submit.getId();
        Review review = findOne(signId);
        review.review(submit);

        Set<ReviewStep> updated = review.getUpdated();
        stepService.update(updated);

        if (review.isFinish()) {
            repository.update(review);
        }

        return ProcessResult.fromReview(review.getStatus());
    }

    @Override
    public ProcessResult reject(Submit submit) {
        long signId = submit.getId();
        Review review = findOne(signId);
        review.reject(submit);

        Set<ReviewStep> updated = review.getUpdated();
        stepService.update(updated);

        if (review.isRejected()) {
            stepService.update(updated);
        }

        return ProcessResult.fromReview(review.getStatus());
    }
}
