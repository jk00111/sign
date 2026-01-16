package com.example.sign.step.service;

import com.example.sign.escalate.Approver;
import com.example.sign.step.entity.ApprovalStep;
import com.example.sign.step.entity.ApprovalStepImpl;
import com.example.sign.step.entity.ProcessStep;
import com.example.sign.step.repository.StepRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ApprovalStepServiceImpl implements ApprovalStepService {

    private final StepRepository repository;

    @Override
    public ProcessStep findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<ApprovalStep> findByApproval(long approvalId) {
        return repository.findByApproval(approvalId);
    }

    @Override
    public void assign(Collection<Approver> approvers) {
        List<ProcessStep> approvalSteps = makeLine(approvers);
        approvalSteps.forEach(repository::create);
    }

    @Override
    public void update(ApprovalStep step) {
        repository.update(step);
    }

    @Override
    public void update(Collection<ApprovalStep> steps) {
        steps.forEach(this::update);
    }

    private List<ProcessStep> makeLine(Collection<Approver> approvers) {
        return approvers.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    private ApprovalStep toEntity(Approver approver) {
        return new ApprovalStepImpl(approver.getApprovalId(), approver.getId());
    }
}
