package com.example.sign.ui;

import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalate;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;
import com.example.sign.ui.result.SignResult;
import com.example.sign.sign.service.SignService;
import com.example.sign.ui.submit.Submit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignImpl implements Sign {

    private final SignService signService;
    private final ApprovalAction approvalAction;
    private final ReviewAction reviewAction;

    @Override
    public SignResult escalate(Escalate escalate) {
        Escalater escalater = escalate.getEscalater();
        Approvals approvals = escalate.getApprovals();
        Reviews reviews = escalate.getReviews();

        long signId = signService.escalate(escalater, approvals, reviews);

        return SignResult.escalated(signId);
    }

    @Override
    public SignResult cancel(Submit cancel) {
        signService.cancel(cancel);

        return SignResult.canceled(cancel.getSignId());
    }

    @Override
    public ApprovalAction approvalAction() {
        return this.approvalAction;
    }

    @Override
    public ReviewAction reviewAction() {
        return this.reviewAction;
    }
}
