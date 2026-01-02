package com.example.sign.line.policy;

import com.example.sign.line.entity.ApprovalLine;
import com.example.sign.line.entity.ApprovalStep;
import com.example.sign.line.enums.StepStatus;
import com.example.sign.vo.ApprovalUser;

public class ApproveAllPolicy implements ApprovalPolicy {

    @Override
    public ApprovalStep apply(ApprovalLine line, ApprovalUser user) {
        ApprovalStep current = line.getCurrent();

        while (line.iterator().hasNext()) {
            ApprovalStep step = line.iterator().next();

            if (isNone(step)) {
                step.pass();
            }

            if (current.equals(step)) {
                step.proceed(user);
            }
        }

        return current;
    }

    private boolean isNone(ApprovalStep step) {
        return StepStatus.NONE.equals(step.status());
    }
}
