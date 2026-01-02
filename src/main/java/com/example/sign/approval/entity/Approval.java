package com.example.sign.approval.entity;

import com.example.sign.approval.enums.ApprovalStatus;
import com.example.sign.event.ApproveEvent;
import com.example.sign.event.CancelEvent;
import com.example.sign.event.RejectEvent;
import com.example.sign.line.entity.ApprovalStep;
import com.example.sign.vo.ApprovalUser;
import lombok.Getter;

import java.util.List;

@Getter
public class Approval {

    private long signId;
    private long id;
    private ApprovalStatus status;
    private ApprovalUser requester;
    private List<ApprovalStep> line;

    public Approval(long signId, long id, ApprovalStatus status, ApprovalUser requester, List<ApprovalStep> line) {
        this.signId = signId;
        this.id = id;
        this.status = status;
        this.requester = requester;
        this.line = line;
    }

    public Approval(long signId, List<ApprovalStep> line) {
        this.signId = signId;
        this.line = line;
        this.status = ApprovalStatus.ESCALATED;
    }

    public Approval(long signId, ApprovalUser requester, List<ApprovalStep> line) {
        this.signId = signId;
        this.requester = requester;
        this.line = line;
        this.status = ApprovalStatus.ESCALATED;
    }

    public long id() {
        return id;
    }

    public void approve(ApproveEvent event) {
        this.status = ApprovalStatus.APPROVED;
    }

    public void reject(RejectEvent event) {
        if (!event.isRejected()) {
            return;
        }
        this.status = ApprovalStatus.REJECTED;
    }

    public void cancel(CancelEvent event) {
        if (!event.isCanceled()) {
            return;
        }

        this.status = ApprovalStatus.CANCELED;
    }
}
