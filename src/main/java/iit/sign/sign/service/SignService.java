package iit.sign.sign.service;

import iit.sign.api.command.Cancel;
import iit.sign.api.command.Escalator;
import iit.sign.common.ProcessResult;
import iit.sign.sign.entity.Sign;
import iit.sign.api.command.Approvals;
import iit.sign.api.command.Reviews;

public interface SignService {

    long escalate(Escalator escalator, Approvals approvals, Reviews reviews);

    void update(long id, ProcessResult processResult);

    void cancel(Cancel cancel);

    Sign findOne(long id);


}
