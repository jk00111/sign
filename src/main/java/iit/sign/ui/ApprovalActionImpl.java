package iit.sign.ui;

import iit.sign.approval.service.ApprovalService;
import iit.sign.ui.result.ProcessResult;
import iit.sign.ui.result.SignResult;
import iit.sign.sign.service.SignService;
import iit.sign.ui.submit.Submit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalActionImpl implements ApprovalAction {

    private final SignService signService;
    private final ApprovalService approvalService;

    @Override
    public SignResult approve(Submit submit) {
        long signId = submit.getId();
        ProcessResult processResult = approvalService.approve(submit);

        signService.update(signId, processResult);

        return SignResult.proceed(signId, processResult.getStatus());
    }

    @Override
    public SignResult reject(Submit submit) {
        long signId = submit.getId();
        ProcessResult processResult = approvalService.reject(submit);

        signService.update(signId, processResult);

        return SignResult.rejected(signId);
    }
}
