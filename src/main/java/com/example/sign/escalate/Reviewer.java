package com.example.sign.escalate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Reviewer {

    private long reviewId;
    private final long id;

    private Reviewer(long reviewId, long id) {
        this.reviewId = reviewId;
        this.id = id;
    }

    public Reviewer(long id) {
        this.id = id;
    }

    public Reviewer addReviewId(long reviewId) {
        return new Reviewer(reviewId, this.id);
    }
}
