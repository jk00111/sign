package com.example.sign.escalate;

import com.example.sign.approval.entity.Approval;
import com.example.sign.step.entity.ApprovalStep;
import com.example.sign.step.entity.ApprovalStepImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class Approvals {

    private final List<Approver> approvers;

    public Approval toEntity(long signId) {
        if (isEmpty()) {
            return Approval.empty();
        }

        return new Approval(signId, makeLine());
    }

    private boolean isEmpty(){
        return approvers.isEmpty();
    }

    private List<ApprovalStep> makeLine() {
        return approvers.stream()
                .map(this::makeStep)
                .collect(Collectors.toList());
    }

    private ApprovalStep makeStep(Approver approver) {
        return new ApprovalStepImpl(approver.getId());
    }

    public Approvals(Approver... approvers) {
        this.approvers = new ArrayList<>(Arrays.asList(approvers));
    }

    public static Approvals empty() {
        return new Approvals(Collections.emptyList());
    }
}
