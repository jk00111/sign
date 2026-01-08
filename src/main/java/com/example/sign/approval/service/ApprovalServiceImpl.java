package com.example.sign.approval.service;

import com.example.sign.result.Result;
import com.example.sign.approval.entity.Approval;
import com.example.sign.approval.repository.ApprovalRepository;
import com.example.sign.step.entity.ProcessStep;
import com.example.sign.submit.Submit;
import com.example.sign.step.entity.ApprovalStep;
import com.example.sign.step.service.StepService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository repository;
    private final StepService stepService;

    @Override
    public Approval findOne(long signId) {
        Approval approval = repository.findBySign(signId);
        List<ApprovalStep> line = stepService.findByApproval(approval.getId());
        approval.setLine(line);
        return approval;
    }

    @Override
    public void escalate(Approval approval) {
        if (approval.isEmpty()) {
            return;
        }
        repository.create(approval);

        approval.escalateLine();
        stepService.create(approval.getLine());
    }

    @Override
    public Result approve(Submit submit) {
        long signId = submit.getSignId();
        Approval approval = findOne(signId);

        approval.approve(submit.getRequesterId());

        List<ProcessStep> updated = approval.getUpdated();
        stepService.update(updated);

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

        List<ProcessStep> updated = approval.getUpdated();
        stepService.update(updated);

        if (approval.isRejected()) {
            repository.update(approval);
        }

        return Result.fromApproval(approval.getStatus());
    }
}
