package iit.sign;


import iit.sign.ui.result.SignResult;
import iit.sign.ui.Sign;
import iit.sign.ui.submit.Submit;
import iit.sign.escalate.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignTest {

    private final Sign sign;

    public void escalate() {
        SignResult signResult = sign.escalate(
                new ApprovalEscalate(
                    new Escalater(1),
                    new Approvals(
                            new Approver(2),
                            new Approver(3)),
                    new Reviews(
                            new Reviewer(4),
                            new Reviewer(5))
        ));

        long id = signResult.getId();
    }

    public void cancel(long signId, long userId) {
        SignResult cancel = sign.cancel(
                new Submit(signId, userId)
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
