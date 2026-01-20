package iit.sign.api;

import iit.sign.api.command.Cancel;
import iit.sign.api.command.Escalate;
import iit.sign.api.result.SignResult;

public interface Sign {

    SignResult escalate(Escalate escalate);

    SignResult cancel(Cancel cancel);

    ApprovalAction approvalAction();

    ReviewAction reviewAction();

}
