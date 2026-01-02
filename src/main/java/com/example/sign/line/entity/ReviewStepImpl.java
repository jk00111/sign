package com.example.sign.line.entity;

import com.example.sign.line.enums.StepStatus;
import com.example.sign.vo.ApprovalUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReviewStepImpl implements ReviewStep {

    @EqualsAndHashCode.Include
    private final long id;

    @EqualsAndHashCode.Include
    private final long reviewerId;

    private StepStatus status;

    @Override
    public long id() {
        return this.id;
    }

    @Override
    public boolean isUpdated() {
        return false;
    }

    @Override
    public void proceed(ApprovalUser user) {
        if (validate(user)) {
            return;
        }

        this.status = StepStatus.REVIEWED;
    }
    @Override
    public void reject(ApprovalUser user) {
        if (validate(user)) {
            return;
        }

        this.status = StepStatus.REJECTED;
    }

    @Override
    public StepStatus status() {
        return this.status;
    }

    private boolean validate(ApprovalUser user) {
        return user.getId() != reviewerId;
    }
}
