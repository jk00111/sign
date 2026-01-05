package com.example.sign.approval.service;

import com.example.sign.approval.ApprovalResult;
import com.example.sign.approval.entity.Approval;
import com.example.sign.approval.submit.Submit;
import com.example.sign.event.ApproveEvent;
import com.example.sign.event.RejectEvent;
import com.example.sign.vo.ApprovalUser;

public interface ApprovalService {

    Approval findOne(long id);

    void escalate(Approval approval);

    ApprovalResult approve(Submit submit);

    ApprovalResult reject(Submit submit);

}
