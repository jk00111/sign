package iit.sign.sign.service;

import iit.sign.ui.result.ProcessResult;
import iit.sign.sign.entity.Sign;
import iit.sign.escalate.Approvals;
import iit.sign.escalate.Escalater;
import iit.sign.escalate.Reviews;
import iit.sign.ui.submit.Submit;

public interface SignService {

    long escalate(Escalater escalater, Approvals approvals, Reviews reviews);

    void update(long id, ProcessResult processResult);

    void cancel(Submit cancel);

    Sign findOne(long id);


}
