package iit.sign.step.entity;

import iit.sign.step.enums.StepStatus;
import lombok.Getter;

@Getter
public class ReviewStepImpl implements ReviewStep {

    private long stepId;

    private long reviewId;

    private final long reviewerId;

    private StepStatus status;

    private boolean updated;

    public ReviewStepImpl(long reviewerId) {
        this.reviewerId = reviewerId;
        this.status = StepStatus.NONE;
    }

    @Override
    public void setProcessId(long processId) {
        this.stepId = processId;
    }

    @Override
    public long id() {
        return this.stepId;
    }

    @Override
    public long deciderId() {
        return this.reviewerId;
    }

    @Override
    public boolean isUpdated() {
        return this.updated;
    }

    @Override
    public void proceed(long requesterId) {
        if (!isValidDecider(requesterId)) {
            return;
        }

        this.status = StepStatus.REVIEWED;
        updateCheck();
    }

    @Override
    public void reject(long requesterId) {
        if (!isValidDecider(requesterId)) {
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

    private boolean isValidDecider(long requesterId) {
        return requesterId == reviewerId;
    }
}
