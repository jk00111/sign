package iit.sign;


import iit.sign.api.Sign;
import iit.sign.api.command.*;
import iit.sign.api.result.SignResult;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SignTest {

    private final Sign sign;

    public void escalate() {
        List<Reviewer> reviewers = new ArrayList<>();
        SignResult signResult = sign.escalate(
                Escalate.reviewThenApproval(
                    new Escalator(1),
                    new Approvals(
                            new Approver(2),
                            new Approver(3)),
                    new Reviews(
                            reviewers
        )));

        long id = signResult.getId();
    }

    public void cancel(long signId, long userId) {
        SignResult cancel = sign.cancel(
                new Cancel(signId, userId)
        );
    }

    public void approve(long signId, long userId) {
        SignResult result = sign.approvalAction()
                .approve(
                        new Submit(signId, userId)
                );

    }

    public void review(long signId, long userId) {
        SignResult result = sign.reviewAction()
                .review(
                        new Submit(signId, userId)
                );
    }

    public void reject(long signId, long userId) {
        // 승인 반려
        SignResult approvalReject = sign.approvalAction()
                .reject(
                        new Submit(signId, userId));


        // 검토 반려
        SignResult reviewReject = sign.reviewAction()
                .reject(
                        new Submit(signId, userId)
                );
    }
}
