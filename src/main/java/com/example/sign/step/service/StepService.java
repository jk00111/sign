package com.example.sign.step.service;

import com.example.sign.step.entity.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface StepService {

    ProcessStep findOne(long id);

    List<ApprovalStep> findByApproval(long approvalId);

    Set<ReviewStep> findByReview(long reviewId);

    void create(ProcessStep step);

    void create(Collection<ProcessStep> steps);

    void update(ProcessStep step);

    void update(Collection<ProcessStep> steps);

}
