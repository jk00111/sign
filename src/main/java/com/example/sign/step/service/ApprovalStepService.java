package com.example.sign.step.service;

import com.example.sign.escalate.Approver;
import com.example.sign.step.entity.*;

import java.util.Collection;
import java.util.List;

public interface ApprovalStepService {

    ProcessStep findOne(long id);

    List<ApprovalStep> findByApproval(long approvalId);

    void assign(Collection<Approver> approvers);

    void update(ApprovalStep step);

    void update(Collection<ApprovalStep> steps);

}
