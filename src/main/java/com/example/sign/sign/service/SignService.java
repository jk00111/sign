package com.example.sign.sign.service;

import com.example.sign.result.Result;
import com.example.sign.submit.CancelSubmit;
import com.example.sign.sign.entity.Sign;
import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;

public interface SignService {

    long escalate(Escalater escalater, Approvals approvals, Reviews reviews);

    void update(long id, Result result);

    void cancel(CancelSubmit cancel);

    Sign findOne(long id);

}
