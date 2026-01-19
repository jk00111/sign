package iit.sign.approval.repository;

import iit.sign.approval.entity.Approval;

public interface ApprovalRepository {

    Approval findBySign(long signId);
    void create(Approval approval);
    void update(Approval approval);

}
