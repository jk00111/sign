package com.example.sign.line.entity;

import com.example.sign.line.enums.StepStatus;
import com.example.sign.line.policy.ApprovalPolicy;
import com.example.sign.line.policy.DefaultApprovalPolicy;
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

    /**
     * 결재 방식 합성 + 위임 vs Line의 다형성
     * 변경되는 것과 변경 되지 않는것 -> 라인의 행동이 바뀔 가능성이 있는가??
     * */
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
