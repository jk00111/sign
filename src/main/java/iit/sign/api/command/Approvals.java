package iit.sign.api.command;

import iit.sign.approval.entity.Approval;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Approvals {

    private long signId;
    private final List<Approver> approvers;

    public Approvals(Approver... approvers) {
        this.approvers = new ArrayList<>(Arrays.asList(approvers));
    }

    public Approvals(Collection<Approver> approvers) {
        this.approvers = new ArrayList<>(approvers);
    }

    private Approvals(long singId, List<Approver> approvers) {
        this.signId = singId;
        this.approvers = approvers;
    }

    public Approval toEntity() {
        if (isEmpty()) {
            return Approval.empty();
        }

        return new Approval(this.signId);
    }

    public Approvals addSignId(long signId) {
       return new Approvals(
               signId,
               this.approvers
       );
    }

    private boolean isEmpty(){
        return approvers.isEmpty();
    }
}
