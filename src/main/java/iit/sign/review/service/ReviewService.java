package iit.sign.review.service;

import iit.sign.escalate.Reviews;
import iit.sign.ui.submit.Submit;
import iit.sign.ui.result.ProcessResult;
import iit.sign.review.entity.Review;

public interface ReviewService {

    void request(Reviews reviews);

    Review findOne(long signId);

    ProcessResult review(Submit submit);

    ProcessResult reject(Submit submit);

}
