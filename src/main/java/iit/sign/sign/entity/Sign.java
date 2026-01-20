package iit.sign.sign.entity;

import iit.sign.api.command.Cancel;
import iit.sign.common.ProcessResult;
import iit.sign.sign.enums.SignStatus;
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

    public void cancel(Cancel cancel) {
        if (!validateRequester(cancel.getRequesterId())) {
            throw new IllegalArgumentException("Only the escalater can cancel this sign request");
        }

        if (isProceed()) {
            throw new IllegalStateException("Cannot cancel a sign that has already proceeded");
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
