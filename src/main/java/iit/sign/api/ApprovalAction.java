package iit.sign.api;

import iit.sign.api.command.Submit;
import iit.sign.api.result.SignResult;

public interface ApprovalAction {

    SignResult approve(Submit submit);

    SignResult reject(Submit submit);

}
