package iit.sign.sign.entity;

import iit.sign.ui.result.ProcessResult;
import iit.sign.sign.enums.SignStatus;
import iit.sign.ui.submit.Submit;
import lombok.Getter;

@Getter
public class Sign {

    private long id;
    private long escalaterId;
    private SignStatus status;

    private Sign() {
    }

    public static Sign create(long escalaterId) {
        Sign sign = new Sign();
        sign.escalaterId = escalaterId;
        sign.status = SignStatus.ESCALATED;
        return sign;
    }

    public void update(ProcessResult processResult) {
        this.status = processResult.getStatus();
    }

    public void cancel(Submit cancel) {
        if (!validateRequester(cancel.getDeciderId())) {
            throw new IllegalArgumentException("unauthorized requester");
        }

        if (isProceed()) {
            throw new IllegalStateException("proceed sign");
        }

        this.status = SignStatus.CANCELED;
    }

    private boolean validateRequester(long requesterId) {
        return this.escalaterId == requesterId;
    }

    private boolean isProceed() {
        return this.status != SignStatus.ESCALATED;
    }
}
