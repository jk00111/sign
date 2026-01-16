package com.example.sign.ui;

import com.example.sign.ui.result.SignResult;
import com.example.sign.ui.submit.Submit;

public interface ReviewAction {

    SignResult review(Submit submit);

    SignResult reject(Submit submit);

}
