package com.example.sign.ui;

import com.example.sign.ui.result.Result;
import com.example.sign.ui.result.SignResult;
import com.example.sign.review.service.ReviewService;
import com.example.sign.sign.service.SignService;
import com.example.sign.ui.submit.Submit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewActionImpl implements ReviewAction {

    private final SignService signService;
    private final ReviewService reviewService;

    @Override
    public SignResult review(Submit submit) {
        long signId = submit.getSignId();
        Result result = reviewService.review(submit);

        signService.update(signId, result);

        return SignResult.proceed(signId, result.getStatus());
    }

    @Override
    public SignResult reject(Submit submit) {
        long signId = submit.getSignId();
        Result result = reviewService.reject(submit);

        signService.update(signId, result);

        return SignResult.rejected(signId);
    }
}
