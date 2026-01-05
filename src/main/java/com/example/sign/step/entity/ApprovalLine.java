package com.example.sign.step.entity;

import com.example.sign.step.enums.StepStatus;
import com.example.sign.step.policy.ApprovalPolicy;
import com.example.sign.step.policy.DefaultApprovalPolicy;
import com.example.sign.vo.ApprovalUser;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class ApprovalLine implements Iterable<ApprovalStep> {

    private final long approvalId;
    private final List<ApprovalStep> steps;
    private final ApprovalPolicy policy;
    private int current;

    public ApprovalLine(long approvalId, List<ApprovalStep> steps) {
        this(approvalId, steps, new DefaultApprovalPolicy());
    }

    public ApprovalLine(long approvalId, List<ApprovalStep> steps, ApprovalPolicy policy) {
        this.approvalId = approvalId;
        this.steps = steps;
        this.policy = policy;
        this.current = current();
    }

    @Override
    public Iterator<ApprovalStep> iterator() {
        return steps.iterator();
    }

    public ApprovalStep getCurrent() {
        return steps.get(this.current);
    }

    public ApprovalStep next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException();
        }

        current++;
        return steps.get(current);
    }

    public boolean hasNext() {
        return this.current < steps.size() + 1;
    }

    public void approve(ApprovalUser approver) {
        policy.apply(this, approver);
    }

    public List<ApprovalStep> getUpdated() {
        return steps.stream().filter(ProcessStep::isUpdated).collect(Collectors.toList());
    }

    public boolean isApproved() {
        return steps.stream()
                .noneMatch(v -> StepStatus.WAITING.equals(v.status()));
    }

    public boolean isRejected() {
        return steps.stream()
                .anyMatch(v -> StepStatus.REJECTED.equals(v.status()));
    }

    private int current() {
        ApprovalStep current = steps.stream()
                .filter(v -> StepStatus.WAITING.equals(v.status()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        return steps.indexOf(current);
    }
}
