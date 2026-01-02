package com.example.sign.approval.service;

import com.example.sign.approval.entity.Approval;
import com.example.sign.approval.repository.ApprovalRepository;
import com.example.sign.event.ApproveEvent;
import com.example.sign.event.RejectEvent;
import com.example.sign.line.service.LineService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final LineService lineService;

    @Override
    public Approval findOne(long id) {
        return approvalRepository.findOne(id);
    }

    @Override
    public void escalate(Approval approval) {
        if (approval == null) {
            return;
        }

        approvalRepository.create(approval);
        lineService.create(approval.getLine());
    }

    @Override
    public void approve(long approvalId, ApproveEvent event) {
        if (!event.isApproved()) {
            return;
        }
        Approval approval = findOne(approvalId);
        approval.approve(event);
        approvalRepository.update(approval);
    }

    @Override
    public void reject(long approvalId, RejectEvent event) {
        Approval approval = findOne(approvalId);

        approval.reject(event);
        approvalRepository.update(approval);
    }
}
