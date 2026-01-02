package com.example.sign;

import com.example.sign.approval.service.ApprovalService;
import com.example.sign.document.dto.Cancel;
import com.example.sign.document.service.SignService;
import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalate;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;
import com.example.sign.event.ApprovalEvent;
import com.example.sign.event.ApproveEvent;
import com.example.sign.event.RejectEvent;
import com.example.sign.event.ReviewEvent;
import com.example.sign.line.entity.ApprovalLine;
import com.example.sign.line.entity.ReviewLine;
import com.example.sign.line.service.LineService;
import com.example.sign.review.service.ReviewService;
import com.example.sign.vo.ApprovalResult;
import com.example.sign.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalFlowService {

    private final SignService signService;
    private final ApprovalService approvalService;
    private final ReviewService reviewService;
    private final LineService lineService;

    public ApprovalResult escalate(Escalate escalate) {
        Escalater escalater = escalate.getEscalater();
        Approvals approvals = escalate.getApprovals();
        Reviews reviews = escalate.getReviews();

        long id = signService.escalate(escalater, approvals, reviews);
        return ApprovalResult.escalated(id);
    }

    public ApprovalResult cancel(Cancel cancel, ApprovalUser requester) {
        signService.cancel(cancel);
        return ApprovalResult.canceled(cancel.getId());
    }

    public ApprovalResult approve(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        ApproveEvent event = lineService.approve(line, user);

        approvalService.approve(id, event);
        return new ApprovalResult(id, event.isApproved());
    }

    public ApprovalResult rejectApproval(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        RejectEvent event = lineService.reject(line, user);

        approvalService.reject(id, event);
        return new ApprovalResult(id, event.isRejected());
    }

    public ApprovalResult review(long id, ApprovalUser user) {
        ReviewLine reviewLine = new ReviewLine(id, lineService.findByReview(id));
        ReviewEvent event = lineService.review(reviewLine, user);

        reviewService.review(id, event);
        return new ApprovalResult(id, event.isReviewed());
    }

    public ApprovalResult rejectReview(long id, ApprovalUser user) {
        ReviewLine reviewLine = new ReviewLine(id, lineService.findByReview(id));
        ApprovalEvent event = lineService.review(reviewLine, user);

        reviewService.reject(id, event);
        return new ApprovalResult(id, event.isRejected());
    }
}
