package com.example.sign.ui;

import com.example.sign.escalate.Escalate;
import com.example.sign.ui.result.SignResult;
import com.example.sign.ui.submit.Submit;

public interface Sign {

    SignResult escalate(Escalate escalate);

    SignResult cancel(Submit cancel);

    ApprovalAction approvalAction();

    ReviewAction reviewAction();


}
