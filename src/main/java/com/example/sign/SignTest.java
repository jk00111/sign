package com.example.sign;


import com.example.sign.escalate.*;
import com.example.sign.ui.result.SignResult;
import com.example.sign.ui.Sign;
import com.example.sign.ui.submit.Submit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignTest {

    private final Sign sign;

    public void escalateTest() {
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

    public void cancelTest(long signId, long userId) {
        SignResult cancel = sign.cancel(
                new Submit(signId, userId)
        );
    }

    public void approveTest(long signId, long userId) {
        SignResult result = sign.approvalAction()
                .approve(
                        new Submit(signId, userId)
                );
    }

    public void reviewTest(long signId, long userId) {
        SignResult result = sign.reviewAction()
                .review(
                        new Submit(signId, userId)
                );
    }

    public void rejectTest(long signId, long userId) {
        SignResult approvalReject = sign.approvalAction()
                .reject(
                        new Submit(signId, userId));

        SignResult reviewReject = sign.reviewAction()
                .reject(
                        new Submit(signId, userId)
                );
    }
}
