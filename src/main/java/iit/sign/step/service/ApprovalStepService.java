package iit.sign.step.service;

import iit.sign.escalate.Approver;
import iit.sign.step.entity.ApprovalStep;
import iit.sign.step.entity.ProcessStep;

import java.util.Collection;
import java.util.List;

public interface ApprovalStepService {

    ProcessStep findOne(long id);

    List<ApprovalStep> findByApproval(long approvalId);

    void assign(Collection<Approver> approvers);

    void update(ApprovalStep step);

    void update(Collection<ApprovalStep> steps);

}
