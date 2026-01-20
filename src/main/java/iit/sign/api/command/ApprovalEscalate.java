package iit.sign.api.command;

import lombok.Getter;

@Getter
public class ApprovalEscalate implements Escalate {

    private final Escalator escalator;

    private final Approvals approvals;

    private final Reviews reviews;

    protected ApprovalEscalate(Escalator escalator, Approvals approvals, Reviews reviews) {
        this.escalator = escalator;
        this.approvals = approvals;
        this.reviews = reviews;
    }
}
