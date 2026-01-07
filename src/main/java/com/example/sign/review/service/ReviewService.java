package com.example.sign.review.service;

import com.example.sign.approval.submit.Submit;
import com.example.sign.result.Result;
import com.example.sign.review.entity.Review;

public interface ReviewService {

    void escalate(Review review);

    Review findOne(long signId);

    Result review(Submit submit);

    Result reject(Submit submit);

}
