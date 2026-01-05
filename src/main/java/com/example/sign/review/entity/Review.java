package com.example.sign.review.entity;

import com.example.sign.event.RejectEvent;
import com.example.sign.event.ReviewEvent;
import com.example.sign.step.entity.ReviewStep;
import com.example.sign.review.enums.ReviewStatus;
import lombok.Getter;

import java.util.Set;

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

    public void review(ReviewEvent event) {
        this.status = ReviewStatus.REVIEWED;
    }

    public void reject(RejectEvent event) {
        if (!event.isRejected()) {
            return;
        }
        this.status = ReviewStatus.REJECTED;
    }


    public static Review empty() {
        return new Review();
    }

    public boolean isEmpty() {
        return ReviewStatus.NONE.equals(this.status);
    }
}