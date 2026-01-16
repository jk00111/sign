package com.example.sign.approval.service;

import com.example.sign.escalate.Approvals;
import com.example.sign.ui.result.Result;
import com.example.sign.approval.entity.Approval;
import com.example.sign.ui.submit.Submit;

public interface ApprovalService {

    Approval findOne(long signId);

    void request(Approvals approvals);

    Result approve(Submit submit);

    Result reject(Submit submit);

}
