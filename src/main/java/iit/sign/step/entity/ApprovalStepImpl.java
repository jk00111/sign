package iit.sign.step.entity;

import iit.sign.step.enums.StepStatus;

public class ApprovalStepImpl implements ApprovalStep {

    private long approvalId;
    private final long approverId;
    private StepStatus status;
    private boolean updated;

    public ApprovalStepImpl(long approvalId, long approverId) {
        this.approvalId = approvalId;
        this.approverId = approverId;
        this.status = StepStatus.NONE;
    }

    public ApprovalStepImpl(long approvalId, long approverId, StepStatus status) {
        this.approvalId = approvalId;
        this.approverId = approverId;
        this.status = status;
    }

    @Override
    public void setProcessId(long processId) {
        this.approvalId = processId;
    }

    @Override
    public long id() {
        return this.approvalId;
    }

    @Override
    public boolean isUpdated() {
        return this.updated;
    }

    @Override
    public void proceed(long requesterId) {
        if (validate(requesterId)) {
            return;
        }

        status = StepStatus.APPROVED;
        updateCheck();
    }

    @Override
    public void reject(long requesterId) {
        if (validate(requesterId)) {
            return;
        }

        status = StepStatus.REJECTED;
        updateCheck();
    }

    @Override
    public void waiting() {
        status = StepStatus.WAITING;
        updateCheck();
    }

    @Override
    public void pass() {
        status = StepStatus.PASSED;
        updateCheck();
    }

    @Override
    public StepStatus status() {
        return status;
    }

    private void updateCheck() {
        this.updated = true;
    }

    private boolean validate(long requesterId) {
        return this.approverId != requesterId;
    }
}
