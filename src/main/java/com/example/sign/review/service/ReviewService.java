package com.example.sign.review.service;

import com.example.sign.escalate.Reviews;
import com.example.sign.ui.submit.Submit;
import com.example.sign.ui.result.Result;
import com.example.sign.review.entity.Review;

public interface ReviewService {

    void request(Reviews reviews);

    Review findOne(long signId);

    Result review(Submit submit);

    Result reject(Submit submit);

}
