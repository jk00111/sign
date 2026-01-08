package com.example.sign.step.service;

import com.example.sign.step.entity.*;
import com.example.sign.step.repository.StepRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class StepServiceImpl implements StepService {

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
    public Set<ReviewStep> findByReview(long reviewId) {
        return repository.findByReview(reviewId);
    }

    @Override
    public void create(ProcessStep step) {
        repository.create(step);
    }

    @Override
    public void create(Collection<ProcessStep> steps) {
        steps.forEach(repository::create);
    }

    @Override
    public void update(ProcessStep step) {
        repository.update(step);
    }

    @Override
    public void update(Collection<ProcessStep> steps) {
        steps.forEach(repository::update);
    }
}
