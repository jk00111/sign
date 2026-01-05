package com.example.sign;


import com.example.sign.escalate.*;
import com.example.sign.vo.SignResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalTest {

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
}
