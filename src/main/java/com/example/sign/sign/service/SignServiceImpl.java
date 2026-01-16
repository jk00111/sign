package com.example.sign.sign.service;

import com.example.sign.ui.result.Result;
import com.example.sign.approval.service.ApprovalService;
import com.example.sign.sign.entity.Sign;
import com.example.sign.sign.repository.SignRepository;
import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;
import com.example.sign.review.service.ReviewService;
import com.example.sign.ui.submit.Submit;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final SignRepository signRepository;
    private final ApprovalService approvalService;
    private final ReviewService reviewService;

    @Override
    public long escalate(Escalater escalater, Approvals approvals, Reviews reviews) {
        Sign sign = Sign.create(escalater.getId());
        signRepository.create(sign);

        final long signId = sign.getId();

        approvalService.request(approvals.addSignId(signId));

        reviewService.request(reviews.addSignId(signId));

        return signId;
    }

    @Override
    public void update(long id, Result result) {
        Sign sign = signRepository.findOne(id);
        sign.update(result);
        signRepository.update(sign);
    }

    @Override
    public void cancel(Submit cancel) {
        long signId = cancel.getSignId();
        Sign sign = signRepository.findOne(signId);
        sign.cancel(cancel);
        signRepository.update(sign);
    }

    @Override
    public Sign findOne(long id) {
        return signRepository.findOne(id);
    }
}
