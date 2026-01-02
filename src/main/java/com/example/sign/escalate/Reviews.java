package com.example.sign.escalate;

import com.example.sign.review.entity.Review;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class Reviews {

    private final Set<Reviewer> reviewers;


    public Reviews(Reviewer... reviewers) {
        this.reviewers = new HashSet<>(Arrays.asList(reviewers));
    }

    public Review toEntity(long signId) {
        return new Review();
    }

    public static Reviews empty() {
        return new Reviews(Collections.emptySet());
    }
}
