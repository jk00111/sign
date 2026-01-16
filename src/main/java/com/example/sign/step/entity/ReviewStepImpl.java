package com.example.sign.step.entity;

import com.example.sign.step.enums.StepStatus;
import lombok.Getter;

@Getter
public class ReviewStepImpl implements ReviewStep {

    private long id;

    private final long reviewerId;

    private StepStatus status;

    private boolean updated;

    public ReviewStepImpl(long reviewerId) {
        this.reviewerId = reviewerId;
        this.status = StepStatus.NONE;
    }

    @Override
    public void setProcessId(long processId) {
        this.id = processId;
    }

    @Override
    public long id() {
        return this.id;
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

        this.status = StepStatus.REVIEWED;
        updateCheck();
    }

    @Override
    public void reject(long requesterId) {
        if (validate(requesterId)) {
            return;
        }

        this.status = StepStatus.REJECTED;
        updateCheck();
    }

    @Override
    public StepStatus status() {
        return this.status;
    }

    private void updateCheck() {
        this.updated = true;
    }

    private boolean validate(long requesterId) {
        return requesterId != reviewerId;
    }
}
