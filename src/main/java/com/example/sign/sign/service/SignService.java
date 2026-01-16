package com.example.sign.sign.service;

import com.example.sign.ui.result.Result;
import com.example.sign.sign.entity.Sign;
import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;
import com.example.sign.ui.submit.Submit;

public interface SignService {

    long escalate(Escalater escalater, Approvals approvals, Reviews reviews);

    void update(long id, Result result);

    void cancel(Submit cancel);

    Sign findOne(long id);

}
