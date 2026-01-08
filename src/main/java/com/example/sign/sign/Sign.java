package com.example.sign.sign;

import com.example.sign.approval.service.ApprovalService;
import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalate;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;
import com.example.sign.result.Result;
import com.example.sign.result.SignResult;
import com.example.sign.review.service.ReviewService;
import com.example.sign.sign.service.SignService;
import com.example.sign.submit.ApprovalSubmit;
import com.example.sign.submit.CancelSubmit;
import com.example.sign.submit.ReviewSubmit;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Sign {

    private final SignService signService;
    private final ApprovalService approvalService;
    private final ReviewService reviewService;

    public SignResult escalate(Escalate escalate) {
        Escalater escalater = escalate.getEscalater();
        Approvals approvals = escalate.getApprovals();
        Reviews reviews = escalate.getReviews();

        long signId = signService.escalate(escalater, approvals, reviews);

        return SignResult.escalated(signId);
    }

    public SignResult cancel(CancelSubmit cancel) {
        signService.cancel(cancel);

        return SignResult.canceled(cancel.getSignId());
    }

    public SignResult approve(ApprovalSubmit submit) {
        long signId = submit.getSignId();
        Result result = approvalService.approve(submit);

        signService.update(signId, result);

        return SignResult.approved(signId, result);
    }

    public SignResult review(ReviewSubmit submit) {
        long signId = submit.getSignId();
        Result result = reviewService.review(submit);

        signService.update(signId, result);

        return SignResult.reviewed(signId, result);
    }

    public SignResult reject(ApprovalSubmit submit) {
        long signId = submit.getSignId();
        Result result = approvalService.reject(submit);

        signService.update(signId, result);

        return SignResult.rejected(signId);
    }

    public SignResult reject(ReviewSubmit submit) {
        long signId = submit.getSignId();
        Result result = reviewService.reject(submit);

        signService.update(signId, result);

        return SignResult.rejected(signId);
    }
}
