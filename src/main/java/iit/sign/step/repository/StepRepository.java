package iit.sign.step.repository;

import iit.sign.step.entity.ApprovalStep;
import iit.sign.step.entity.ProcessStep;
import iit.sign.step.entity.ReviewStep;

import java.util.List;
import java.util.Set;

public interface StepRepository {

    void create(ProcessStep step);

    void update(ProcessStep step);

    ProcessStep findOne(long id);

    List<ApprovalStep> findByApproval(long approvalId);

    Set<ReviewStep> findByReview(long reviewId);
}
