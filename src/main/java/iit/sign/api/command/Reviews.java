package iit.sign.api.command;

import iit.sign.review.entity.Review;
import lombok.Getter;

import java.util.*;

@Getter
public class Reviews {

    private long signId;
    private final Set<Reviewer> reviewers;

    public Reviews(Reviewer... reviewers) {
        this.reviewers = new HashSet<>(Arrays.asList(reviewers));
    }

    private Reviews(long signId, Set<Reviewer> reviewers) {
        this.signId = signId;
        this.reviewers = reviewers;
    }

    private Reviews(Set<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }

    public Reviews addSignId(long signId) {
        return new Reviews(signId, this.reviewers);
    }

    public Review toEntity() {
        if (isEmpty()) {
            return Review.empty();
        }

        return new Review(this.signId);
    }

    public static Reviews empty() {
        return new Reviews(Collections.emptySet());
    }


    private boolean isEmpty() {
        return this.reviewers.isEmpty();
    }
}
