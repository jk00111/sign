package com.example.sign.approval.service;

import com.example.sign.approval.ApprovalResult;
import com.example.sign.approval.entity.Approval;
import com.example.sign.approval.repository.ApprovalRepository;
import com.example.sign.approval.submit.Submit;
import com.example.sign.step.entity.ApprovalStep;
import com.example.sign.step.service.StepService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final StepService stepService;

    @Override
    public Approval findOne(long id) {
        Approval approval = approvalRepository.findBySign(id);
        List<ApprovalStep> line = stepService.findByApproval(id);
        approval.setLine(line);
        return approval;
    }

    @Override
    public void escalate(Approval approval) {
        if (approval.isEmpty()) {
            return;
        }
        approvalRepository.create(approval);

        approval.escalateLine();
        stepService.create(approval.getLine());
    }

    @Override
    public ApprovalResult approve(Submit submit) {
        long signId = submit.getSignId();
        Approval approval = findOne(signId);

        approval.approve(submit.getRequesterId());

        List<ApprovalStep> updated = approval.getUpdated();
        stepService.update(updated);

        update(approval);

        return new ApprovalResult(approval.getStatus());
    }

    @Override
    public ApprovalResult reject(Submit submit) {
        long signId = submit.getSignId();
        Approval approval = findOne(signId);

        approval.reject(submit.getRequesterId());

        List<ApprovalStep> updated = approval.getUpdated();
        stepService.update(updated);

        update(approval);

        return new ApprovalResult(approval.getStatus());
    }

    private void update(Approval approval) {
        approvalRepository.update(approval);
    }
}
