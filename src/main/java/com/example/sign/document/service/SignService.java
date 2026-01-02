package com.example.sign.document.service;

import com.example.sign.document.dto.Cancel;
import com.example.sign.document.entity.Sign;
import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;

public interface SignService {

    long escalate(Escalater escalater, Approvals approvals, Reviews reviews);

    void update(Cancel dto);

    void cancel(Cancel dto);

    Sign findOne(long id);

}
