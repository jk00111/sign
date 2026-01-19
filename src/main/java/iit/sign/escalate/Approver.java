package iit.sign.escalate;

import lombok.Getter;

@Getter
public class Approver {

    private long approvalId;
    private final long id;


    private Approver(long approvalId, long id) {
        this.approvalId = approvalId;
        this.id = id;
    }

    public Approver(long id) {
        this.id = id;
    }

    public Approver addApprovalId(long approvalId) {
        return new Approver(approvalId, this.id);
    }
}
