package com.example.sign.step.service;

import com.example.sign.step.entity.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface StepService {

    ProcessStep findOne(long id);

    List<ApprovalStep> findByApproval(long approvalId);

    Set<ReviewStep> findByReview(long reviewId);

    void create(List<ApprovalStep> line);

    void create(Set<ReviewStep> line);

    void update(ApprovalStep step);

    void update(Collection<ApprovalStep> steps);

}
