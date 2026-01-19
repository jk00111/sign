package iit.sign.ui;

import iit.sign.escalate.Approvals;
import iit.sign.escalate.Escalate;
import iit.sign.escalate.Escalater;
import iit.sign.escalate.Reviews;
import iit.sign.sign.service.SignService;
import iit.sign.ui.result.SignResult;
import iit.sign.ui.submit.Submit;
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

        return SignResult.canceled(cancel.getId());
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
