package com.example.sign.sign.service;

import com.example.sign.result.Result;
import com.example.sign.approval.entity.Approval;
import com.example.sign.approval.service.ApprovalService;
import com.example.sign.sign.dto.Cancel;
import com.example.sign.sign.entity.Sign;
import com.example.sign.sign.repository.SignRepository;
import com.example.sign.escalate.Approvals;
import com.example.sign.escalate.Escalater;
import com.example.sign.escalate.Reviews;
import com.example.sign.review.entity.Review;
import com.example.sign.review.service.ReviewService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final SignRepository signRepository;
    private final ApprovalService approvalService;
    private final ReviewService reviewService;

    @Override
    public long escalate(Escalater escalater, Approvals approvals, Reviews reviews) {
        long signId = write(escalater);

        Approval approval = approvals.toEntity(signId);
        approvalService.escalate(approval);

        Review review = reviews.toEntity(signId);
        reviewService.escalate(review);

        return signId;
    }

    private long write(Escalater escalater) {
        Sign sign = Sign.create(escalater.getId());
        signRepository.create(sign);

        return sign.getId();
    }

    @Override
    public void update(long id, Result result) {
        Sign sign = signRepository.findOne(id);
        sign.update(result);
        signRepository.update(sign);
    }

    @Override
    public void cancel(Cancel cancel) {
        long id = cancel.getId();
        Sign sign = signRepository.findOne(id);
        sign.cancel(cancel);
        signRepository.update(sign);
    }

    @Override
    public Sign findOne(long id) {
        return signRepository.findOne(id);
    }
}
