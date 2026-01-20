package iit.sign.approval.service;

import iit.sign.api.ApprovalAction;
import iit.sign.api.command.Submit;
import iit.sign.api.result.SignResult;
import iit.sign.common.ProcessResult;
import iit.sign.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApprovalActionFacade implements ApprovalAction {

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
