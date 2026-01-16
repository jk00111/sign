package com.example.sign.ui;

import com.example.sign.approval.service.ApprovalService;
import com.example.sign.ui.result.Result;
import com.example.sign.ui.result.SignResult;
import com.example.sign.sign.service.SignService;
import com.example.sign.ui.submit.Submit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalActionImpl implements ApprovalAction {

    private final SignService signService;
    private final ApprovalService approvalService;

    @Override
    public SignResult approve(Submit submit) {
        long signId = submit.getSignId();
        Result result = approvalService.approve(submit);

        signService.update(signId, result);

        return SignResult.proceed(signId, result.getStatus());
    }

    @Override
    public SignResult reject(Submit submit) {
        long signId = submit.getSignId();
        Result result = approvalService.reject(submit);

        signService.update(signId, result);

        return SignResult.rejected(signId);
    }
}
