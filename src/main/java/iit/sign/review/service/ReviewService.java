package iit.sign.review.service;

import iit.sign.api.command.Reviews;
import iit.sign.api.command.Submit;
import iit.sign.common.ProcessResult;
import iit.sign.review.entity.Review;

public interface ReviewService {

    void escalate(Reviews reviews);

    Review findOne(long signId);

    ProcessResult review(Submit submit);

    ProcessResult reject(Submit submit);

}
