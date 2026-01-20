package iit.sign.approval.service;

import iit.sign.api.command.Approvals;
import iit.sign.api.command.Submit;
import iit.sign.common.ProcessResult;
import iit.sign.approval.entity.Approval;

public interface ApprovalService {

    Approval findOne(long signId);

    void escalate(Approvals approvals);

    ProcessResult approve(Submit submit);

    ProcessResult reject(Submit submit);

}
