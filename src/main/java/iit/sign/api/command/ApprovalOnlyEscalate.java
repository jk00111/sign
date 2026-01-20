package iit.sign.api.command;

import lombok.Getter;

@Getter
public class ApprovalOnlyEscalate implements Escalate {

    private final Escalator escalator;

    private final Approvals approvals;

    private final Reviews reviews = Reviews.empty();

    protected ApprovalOnlyEscalate(Escalator escalator, Approvals approvals) {
        this.escalator = escalator;
        this.approvals = approvals;
    }
}
