package iit.sign.ui;

import iit.sign.escalate.Escalate;
import iit.sign.ui.result.SignResult;
import iit.sign.ui.submit.Submit;

public interface Sign {

    SignResult escalate(Escalate escalate);

    SignResult cancel(Submit cancel);

    ApprovalAction approvalAction();

    ReviewAction reviewAction();


}
