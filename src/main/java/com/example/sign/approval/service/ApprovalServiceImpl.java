package com.example.sign.approval.service;

import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Approver;
import com.example.sign.ui.result.Result;
import com.example.sign.approval.entity.Approval;
import com.example.sign.approval.repository.ApprovalRepository;
import com.example.sign.ui.submit.Submit;
import com.example.sign.step.entity.ApprovalStep;
import com.example.sign.step.service.ApprovalStepService;
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
    public Result approve(Submit submit) {
        long signId = submit.getSignId();
        Approval approval = findOne(signId);

        approval.approve(submit.getRequesterId());

        List<ApprovalStep> updated = approval.getUpdated();
        approvalStepService.update(updated);

        if (approval.isFinish()) {
            repository.update(approval);
        }

        return Result.fromApproval(approval.getStatus());
    }

    @Override
    public Result reject(Submit submit) {
        long signId = submit.getSignId();
        Approval approval = findOne(signId);

        approval.reject(submit.getRequesterId());

        List<ApprovalStep> updated = approval.getUpdated();
        approvalStepService.update(updated);

        if (approval.isRejected()) {
            repository.update(approval);
        }

        return Result.fromApproval(approval.getStatus());
    }
}
