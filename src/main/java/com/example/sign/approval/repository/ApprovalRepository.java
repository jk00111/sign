package com.example.sign.approval.repository;

import com.example.sign.approval.entity.Approval;

public interface ApprovalRepository {

    Approval findOne(long id);
    void create(Approval approval);
    void update(Approval approval);
}
