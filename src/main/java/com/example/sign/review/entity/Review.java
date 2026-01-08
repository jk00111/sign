package com.example.sign.review.entity;

import com.example.sign.review.enums.ReviewStatus;
import com.example.sign.step.entity.ProcessStep;
import com.example.sign.step.entity.ReviewStep;
import com.example.sign.step.enums.StepStatus;
import com.example.sign.submit.Submit;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Review {

    private long signId;
    private long id;
    private ReviewStatus status;
    private Set<ReviewStep> line;

    private Review() {
        this.status = ReviewStatus.EMPTY;
    }

    public Review(long signId, Set<ReviewStep> line) {
        this.signId = signId;
        this.line = line;
        this.status = ReviewStatus.ESCALATED;
    }

    public void escalateLine() {
        line.forEach(step -> step.escalate(this.id));
    }

    public Set<ProcessStep> getLine() {
        return new HashSet<>(this.line);
    }

    public void review(Submit submit) {
        long requesterId = submit.getRequesterId();
        ReviewStep target = getStep(requesterId);
        target.proceed(requesterId);

        if (isFinish()) {
            this.status = ReviewStatus.REVIEWED;
        }
    }

    public Set<ProcessStep> getUpdated() {
        return line.stream()
                .filter(ProcessStep::isUpdated)
                .collect(Collectors.toSet());
    }

    public void reject(Submit submit) {
        long requesterId = submit.getRequesterId();
        ReviewStep target = getStep(requesterId);
        target.reject(requesterId);

        this.status = ReviewStatus.REJECTED;
    }

    public void setLine(Set<ReviewStep> steps) {
        this.line = steps;
    }


    public static Review empty() {
        return new Review();
    }

    public boolean isEmpty() {
        return ReviewStatus.EMPTY.equals(this.status);
    }

    public boolean isFinish() {
        return line.stream()
                .anyMatch(step -> StepStatus.REJECTED.equals(step.status()));
    }

    public boolean isRejected() {
        return ReviewStatus.REJECTED.equals(this.status);
    }

    private ReviewStep getStep(long requesterId) {
        return line.stream()
                .filter(step -> step.id() == requesterId)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}