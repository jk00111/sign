package com.example.sign.approval.service;

import com.example.sign.approval.entity.Approval;
import com.example.sign.event.ApproveEvent;
import com.example.sign.event.RejectEvent;

public interface ApprovalService {

    Approval findOne(long id);

    void escalate(Approval approval);

    void approve(long approvalId, ApproveEvent event);

    void reject(long approvalId, RejectEvent event);

}
