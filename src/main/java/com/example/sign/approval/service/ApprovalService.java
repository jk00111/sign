package com.example.sign.approval.service;

import com.example.sign.result.Result;
import com.example.sign.approval.entity.Approval;
import com.example.sign.submit.Submit;
import com.example.sign.submit.Submit;

public interface ApprovalService {

    Approval findOne(long signId);

    void escalate(Approval approval);

    Result approve(Submit submit);

    Result reject(Submit submit);

}
