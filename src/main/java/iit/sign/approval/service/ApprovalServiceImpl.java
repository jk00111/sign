package iit.sign.approval.service;

import iit.sign.escalate.Approvals;
import iit.sign.escalate.Approver;
import iit.sign.ui.result.ProcessResult;
import iit.sign.approval.entity.Approval;
import iit.sign.approval.repository.ApprovalRepository;
import iit.sign.ui.submit.Submit;
import iit.sign.step.entity.ApprovalStep;
import iit.sign.step.service.ApprovalStepService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository repository;
    private final ApprovalStepService approvalStepService;

    @Override
    public Approval findOne(long signId) {
        Approval approval = repository.findBySign(signId);
        List<ApprovalStep> line = approvalStepService.findByApproval(approval.getId());
        approval.setLine(line);
        return approval;
    }

    @Override
    public void request(Approvals approvals) {
        Approval approval = approvals.toEntity();

        if (approval.isEmpty()) {
            return;
        }

        repository.create(approval);

        List<Approver> approvers = approvals.getApprovers();
        long approvalId = approval.getId();

        List<Approver> added = approvers.stream()
                .map(approver -> approver.addApprovalId(approvalId))
                .collect(Collectors.toList());
        approvalStepService.assign(added);
    }

    @Override
    public ProcessResult approve(Submit submit) {
        long signId = submit.getId();
        Approval approval = findOne(signId);

        approval.approve(submit.getDeciderId());

        List<ApprovalStep> updated = approval.getUpdated();
        approvalStepService.update(updated);

        if (approval.isFinish()) {
            repository.update(approval);
        }

        return ProcessResult.fromApproval(approval.getStatus());
    }

    @Override
    public ProcessResult reject(Submit submit) {
        long signId = submit.getId();
        Approval approval = findOne(signId);

        approval.reject(submit.getDeciderId());

        List<ApprovalStep> updated = approval.getUpdated();
        approvalStepService.update(updated);

        if (approval.isRejected()) {
            repository.update(approval);
        }

        return ProcessResult.fromApproval(approval.getStatus());
    }
}
