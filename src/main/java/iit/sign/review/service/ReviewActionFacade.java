package iit.sign.review.service;

import iit.sign.api.ReviewAction;
import iit.sign.api.command.Submit;
import iit.sign.api.result.SignResult;
import iit.sign.common.ProcessResult;
import iit.sign.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewActionFacade implements ReviewAction {

    private final SignService signService;
    private final ReviewService reviewService;

    @Override
    public SignResult review(Submit submit) {
        long signId = submit.getId();
        ProcessResult processResult = reviewService.review(submit);

        signService.update(signId, processResult);

        return SignResult.proceed(signId, processResult.getStatus());
    }

    @Override
    public SignResult reject(Submit submit) {
        long signId = submit.getId();
        ProcessResult processResult = reviewService.reject(submit);

        signService.update(signId, processResult);

        return SignResult.rejected(signId);
    }
}
