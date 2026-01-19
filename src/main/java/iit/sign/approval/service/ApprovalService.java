package iit.sign.approval.service;

import iit.sign.escalate.Approvals;
import iit.sign.ui.result.ProcessResult;
import iit.sign.approval.entity.Approval;
import iit.sign.ui.submit.Submit;

public interface ApprovalService {

    Approval findOne(long signId);

    void request(Approvals approvals);

    ProcessResult approve(Submit submit);

    ProcessResult reject(Submit submit);

}
