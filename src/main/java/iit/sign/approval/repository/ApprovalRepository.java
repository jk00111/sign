package iit.sign.approval.repository;

import iit.sign.approval.entity.Approval;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository {

    Approval findBySignId(long signId);
    void create(Approval approval);
    void update(Approval approval);

}
