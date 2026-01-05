package com.example.sign.approval.repository;

import com.example.sign.approval.entity.Approval;

public interface ApprovalRepository {

    Approval findBySign(long signId);
    void create(Approval approval);
    void update(Approval approval);

}
