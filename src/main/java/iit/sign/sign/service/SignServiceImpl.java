package iit.sign.sign.service;

import iit.sign.ui.result.ProcessResult;
import iit.sign.approval.service.ApprovalService;
import iit.sign.sign.entity.Sign;
import iit.sign.sign.repository.SignRepository;
import iit.sign.escalate.Approvals;
import iit.sign.escalate.Escalater;
import iit.sign.escalate.Reviews;
import iit.sign.review.service.ReviewService;
import iit.sign.ui.submit.Submit;
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
    public void update(long id, ProcessResult processResult) {
        Sign sign = signRepository.findOne(id);
        sign.update(processResult);
        signRepository.update(sign);
    }

    @Override
    public void cancel(Submit cancel) {
        long signId = cancel.getId();
        Sign sign = signRepository.findOne(signId);
        sign.cancel(cancel);
        signRepository.update(sign);
    }

    @Override
    public Sign findOne(long id) {
        return signRepository.findOne(id);
    }
}
