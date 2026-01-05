package com.example.sign.approval;

import com.example.sign.approval.enums.ApprovalStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalResult {

    private final ApprovalStatus status;

    public boolean isApproved() {
        return ApprovalStatus.APPROVED.equals(this.status);
    }

    public boolean isRejected() {
        return ApprovalStatus.REJECTED.equals(this.status);
    }
}
