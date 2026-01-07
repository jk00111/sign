package com.example.sign;

import com.example.sign.approval.service.ApprovalService;
import com.example.sign.approval.submit.Submit;
import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalate;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;
import com.example.sign.result.Result;
import com.example.sign.review.service.ReviewService;
import com.example.sign.sign.dto.Cancel;
import com.example.sign.sign.service.SignService;
import com.example.sign.vo.SignResult;
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

    public SignResult cancel(Cancel cancel) {
        signService.cancel(cancel);
        return SignResult.canceled(cancel.getId());
    }

    public SignResult approve(Submit submit) {
        long signId = submit.getSignId();
        Result result = approvalService.approve(submit);
        signService.update(signId, result);

        return SignResult.approved(signId, result);
    }

    public SignResult rejectApproval(Submit submit) {
        long signId = submit.getSignId();
        Result result = approvalService.reject(submit);
        signService.update(signId, result);

        return SignResult.rejected(signId);
    }

    public SignResult review(Submit submit) {
        long signId = submit.getSignId();
        Result result = reviewService.review(submit);
        signService.update(signId, result);
        return SignResult.approved(signId, result);
    }

    public SignResult rejectReview(Submit submit) {
        long signId = submit.getSignId();
        Result result = reviewService.reject(submit);
        signService.update(signId, result);

        return SignResult.rejected(signId);
    }
}
