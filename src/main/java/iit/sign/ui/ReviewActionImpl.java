package iit.sign.ui;

import iit.sign.ui.result.ProcessResult;
import iit.sign.ui.result.SignResult;
import iit.sign.review.service.ReviewService;
import iit.sign.sign.service.SignService;
import iit.sign.ui.submit.Submit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewActionImpl implements ReviewAction {

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
