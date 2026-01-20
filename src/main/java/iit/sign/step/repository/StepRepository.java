package iit.sign.step.repository;

import iit.sign.step.entity.ApprovalStep;
import iit.sign.step.entity.ProcessStep;
import iit.sign.step.entity.ReviewStep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StepRepository {

    void create(ProcessStep step);

    void update(ProcessStep step);

    ProcessStep findOne(long id);

    List<ApprovalStep> findByApproval(long approvalId);

    Set<ReviewStep> findByReview(long reviewId);
}
