package com.example.sign.review.entity;

import com.example.sign.approval.submit.Submit;
import com.example.sign.event.RejectEvent;
import com.example.sign.event.ReviewEvent;
import com.example.sign.step.entity.ProcessStep;
import com.example.sign.step.entity.ReviewStep;
import com.example.sign.review.enums.ReviewStatus;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Review {

    private long signId;
    private long id;
    private ReviewStatus status;
    private Set<ReviewStep> line;

    private Review() {
        this.status = ReviewStatus.NONE;
    }

    public Review(long signId, Set<ReviewStep> line) {
        this.signId = signId;
        this.line = line;
        this.status = ReviewStatus.ESCALATED;
    }

    public void escalateLine() {
        line.forEach(step -> step.escalate(this.id));
    }

    public void review(Submit submit) {
        long requesterId = submit.getRequesterId();
        ReviewStep target = target(requesterId);
        target.proceed(requesterId);

        if (isFinish()) {
            this.status = ReviewStatus.REVIEWED;
        }
    }

    public Set<ReviewStep> getUpdated() {
        return line.stream()
                .filter(ProcessStep::isUpdated)
                .collect(Collectors.toSet());
    }

    public void reject(Submit submit) {
        long requesterId = submit.getRequesterId();
        ReviewStep target = target(requesterId);
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
        return ReviewStatus.NONE.equals(this.status);
    }

    public boolean isFinish() {
        return true;
    }

    private ReviewStep target(long requesterId) {
        return line.stream()
                .filter(step -> step.id() == requesterId)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}