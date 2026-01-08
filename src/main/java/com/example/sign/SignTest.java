package com.example.sign;


import com.example.sign.escalate.*;
import com.example.sign.result.SignResult;
import com.example.sign.sign.Sign;
import com.example.sign.submit.Submit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignTest {

    private final Sign sign;

    public void escalateTest() {
        SignResult signResult = sign.escalate(
                new ApprovalAndReviewEscalate(
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

    public void approveTest(long signId, long userId) {
        SignResult result = sign.approve(Submit.ofApproval(signId, userId));

    }

    public void rejectTest(long signId, long userId) {
        SignResult approvalResult = sign.reject(Submit.ofApproval(signId, userId));
        SignResult reviewResult = sign.reject(Submit.ofReview(signId, userId));
    }
}
