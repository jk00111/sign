package com.example.sign.line.policy;

import com.example.sign.line.entity.ApprovalLine;
import com.example.sign.line.entity.ApprovalStep;
import com.example.sign.vo.ApprovalUser;

public class DefaultApprovalPolicy implements ApprovalPolicy {

    @Override
    public ApprovalStep apply(ApprovalLine line, ApprovalUser user) {
        ApprovalStep current = line.getCurrent();
        current.proceed(user);

        if (line.hasNext()) {
            ApprovalStep next = line.next();
            next.waiting();
        }

        return current;
    }
}
