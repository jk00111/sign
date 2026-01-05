package com.example.sign.step.entity;

import com.example.sign.step.enums.StepStatus;

public class ApprovalStepImpl implements ApprovalStep {

    private long approvalId;
    private final long approverId;
    private StepStatus status;

    public ApprovalStepImpl(long approverId) {
        this.approverId = approverId;
        this.status = StepStatus.NONE;
    }

    public ApprovalStepImpl(long approvalId, long approverId, StepStatus status) {
        this.approvalId = approvalId;
        this.approverId = approverId;
        this.status = status;
    }

    @Override
    public void escalate(long processId) {
        this.approvalId = processId;
    }

    @Override
    public long id() {
        return this.approvalId;
    }

    @Override
    public boolean isUpdated() {
        return false;
    }

    @Override
    public void proceed(long requesterId) {
        if (validate(requesterId)) {
            return;
        }

        status = StepStatus.APPROVED;
    }

    @Override
    public void reject(long requesterId) {
        if (validate(requesterId)) {
            return;
        }

        status = StepStatus.REJECTED;
    }

    @Override
    public void waiting() {
        status = StepStatus.WAITING;
    }

    @Override
    public void pass() {
        status = StepStatus.PASSED;
    }

    @Override
    public StepStatus status() {
        return status;
    }

    private boolean validate(long requesterId) {
        return this.approverId != requesterId;
    }
}
