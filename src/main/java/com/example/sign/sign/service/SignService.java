package com.example.sign.sign.service;

import com.example.sign.approval.ApprovalResult;
import com.example.sign.approval.submit.Submit;
import com.example.sign.event.ApprovalEvent;
import com.example.sign.sign.dto.Cancel;
import com.example.sign.sign.entity.Sign;
import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;

public interface SignService {

    long escalate(Escalater escalater, Approvals approvals, Reviews reviews);

    void update(long id, ApprovalResult result);

    void cancel(Cancel dto);

    Sign findOne(long id);

}
