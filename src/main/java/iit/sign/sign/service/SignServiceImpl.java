package iit.sign.sign.service;

import iit.sign.api.command.Cancel;
import iit.sign.api.command.Escalator;
import iit.sign.common.ProcessResult;
import iit.sign.approval.service.ApprovalService;
import iit.sign.sign.entity.Sign;
import iit.sign.sign.repository.SignRepository;
import iit.sign.api.command.Approvals;
import iit.sign.api.command.Reviews;
import iit.sign.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService {

    private final SignRepository signRepository;
    private final ApprovalService approvalService;
    private final ReviewService reviewService;

    @Override
    public long escalate(Escalator escalator, Approvals approvals, Reviews reviews) {
        Sign sign = Sign.create(escalator.getId());
        signRepository.create(sign);

        final long signId = sign.getId();

        approvalService.escalate(approvals.addSignId(signId));

        reviewService.escalate(reviews.addSignId(signId));

        return signId;
    }

    @Override
    public void update(long id, ProcessResult processResult) {
        Sign sign = signRepository.findOne(id);
        sign.update(processResult);
        signRepository.update(sign);
    }

    @Override
    public void cancel(Cancel cancel) {
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
