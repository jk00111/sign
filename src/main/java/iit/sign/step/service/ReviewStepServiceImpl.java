package iit.sign.step.service;

import iit.sign.api.command.Reviewer;
import iit.sign.step.entity.ReviewStep;
import iit.sign.step.entity.ReviewStepImpl;
import iit.sign.step.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewStepServiceImpl implements ReviewStepService {

    private final StepRepository repository;

    @Override
    public Set<ReviewStep> findByReview(long reviewId) {
        return repository.findByReview(reviewId);
    }

    @Override
    public void assign(Collection<Reviewer> reviewers) {
        Set<ReviewStep> reviewSteps = makeLine(reviewers);
        reviewSteps.forEach(repository::create);
    }

    @Override
    public void update(ReviewStep step) {
        repository.update(step);
    }

    @Override
    public void update(Collection<ReviewStep> steps) {
        steps.forEach(this::update);
    }

    private Set<ReviewStep> makeLine(Collection<Reviewer> reviewers) {
        return reviewers.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    private ReviewStep toEntity(Reviewer reviewer) {
        return new ReviewStepImpl(reviewer.getId());
    }
}
