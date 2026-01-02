package com.example.sign.review.entity;

import com.example.sign.event.CancelEvent;
import com.example.sign.event.RejectEvent;
import com.example.sign.event.ReviewEvent;
import com.example.sign.line.entity.ReviewStep;
import com.example.sign.review.dto.ReviewDto;
import com.example.sign.review.enums.ReviewStatus;
import com.example.sign.vo.ApprovalUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public class Review {

    private long id;
    private String contents;
    private ReviewStatus status;
    private ApprovalUser requester;
    private Set<ReviewStep> steps;

    public long id() {
        return id;
    }

    public static Review escalate(ReviewDto reviewDto, ApprovalUser requester) {
        return new Review();
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

    public void cancel(CancelEvent event) {
        if (!event.isCanceled()) {
            return;
        }

        this.status = ReviewStatus.CANCELED;
    }
}
