package iit.sign.sign.service;

import iit.sign.api.ApprovalAction;
import iit.sign.api.ReviewAction;
import iit.sign.api.Sign;
import iit.sign.api.command.Cancel;
import iit.sign.api.command.Approvals;
import iit.sign.api.command.Escalate;
import iit.sign.api.command.Escalator;
import iit.sign.api.command.Reviews;
import iit.sign.api.result.SignResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignFacade implements Sign {

    private final SignService signService;
    private final ApprovalAction approvalAction;
    private final ReviewAction reviewAction;

    @Override
    public SignResult escalate(Escalate escalate) {
        Escalator escalator = escalate.getEscalator();
        Approvals approvals = escalate.getApprovals();
        Reviews reviews = escalate.getReviews();

        long signId = signService.escalate(escalator, approvals, reviews);
        return SignResult.escalated(signId);
    }

    @Override
    public SignResult cancel(Cancel cancel) {
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
