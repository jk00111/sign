package com.example.sign.step.entity;

import com.example.sign.step.enums.StepStatus;
import com.example.sign.vo.ApprovalUser;
import lombok.Getter;

@Getter
public class ReviewStepImpl implements ReviewStep {

    private long id;

    private final long reviewerId;

    private StepStatus status;

    public ReviewStepImpl(long reviewerId) {
        this.reviewerId = reviewerId;
        this.status = StepStatus.NONE;
    }

    @Override
    public void escalate(long processId) {
        this.id = processId;
    }

    @Override
    public long id() {
        return this.id;
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

        this.status = StepStatus.REVIEWED;
    }

    @Override
    public void reject(long requesterId) {
        if (validate(requesterId)) {
            return;
        }

        this.status = StepStatus.REJECTED;
    }

    @Override
    public StepStatus status() {
        return this.status;
    }

    private boolean validate(long requesterId) {
        return requesterId != reviewerId;
    }
}
