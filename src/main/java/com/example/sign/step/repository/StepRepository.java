package com.example.sign.step.repository;

import com.example.sign.step.entity.ApprovalStep;
import com.example.sign.step.entity.ProcessStep;
import com.example.sign.step.entity.ReviewStep;

import java.util.List;
import java.util.Set;

public interface StepRepository {

    void create(ProcessStep step);

    void update(ProcessStep step);

    void delete(ProcessStep step);

    ProcessStep findOne(long id);

    List<ApprovalStep> findByApproval(long approvalId);

    Set<ReviewStep> findByReview(long reviewId);
}
