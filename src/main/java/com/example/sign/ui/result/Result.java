package com.example.sign.ui.result;

import com.example.sign.approval.enums.ApprovalStatus;
import com.example.sign.review.enums.ReviewStatus;
import com.example.sign.sign.enums.SignStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Result {
    
    private final SignStatus status;

    public static Result fromApproval(ApprovalStatus status) {
        if (ApprovalStatus.APPROVED.equals(status)) {
            return new Result(SignStatus.APPROVED);
        }

        if (ApprovalStatus.REJECTED.equals(status)) {
            return new Result(SignStatus.REJECTED);
        }

        return new Result(SignStatus.APPROVING);
    }

    public static Result fromReview(ReviewStatus status) {
        if (ReviewStatus.REVIEWED.equals(status)) {
            return new Result(SignStatus.REVIEWED);
        }

        if (ReviewStatus.REJECTED.equals(status)) {
           return new Result(SignStatus.REJECTED);
        }

        return new Result(SignStatus.REVIEWING);
    }
}
