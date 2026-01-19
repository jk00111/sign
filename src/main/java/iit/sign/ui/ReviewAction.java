package iit.sign.ui;

import iit.sign.ui.result.SignResult;
import iit.sign.ui.submit.Submit;

public interface ReviewAction {

    SignResult review(Submit submit);

    SignResult reject(Submit submit);

}
