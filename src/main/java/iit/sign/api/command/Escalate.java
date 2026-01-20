package iit.sign.api.command;

public interface Escalate {

    Escalator getEscalator();

    Approvals getApprovals();

    Reviews getReviews();

    static Escalate approvalOnly(Escalator escalator, Approvals approvals) {
        return new ApprovalOnlyEscalate(escalator, approvals);
    }

    static Escalate reviewThenApproval(Escalator escalator, Approvals approvals, Reviews reviews) {
        return new ApprovalEscalate(escalator, approvals, reviews);
    }
}
