package com.example.sign.escalate;

import com.example.sign.approval.entity.Approval;
import com.example.sign.line.entity.ApprovalStep;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Approvals {

    private final List<Approver> approvers;

    public Approval toEntity(long signId) {
        return new Approval(signId, new ArrayList<>());
    }

    private ApprovalStep to(Approver approver) {
        return null;
    }

    public Approvals(Approver... approvers) {
        this.approvers = new ArrayList<>(Arrays.asList(approvers));
    }

    public static Approvals empty() {
        return new Approvals(Collections.emptyList());
    }
}
