package com.example.sign.review.service;

import com.example.sign.event.RejectEvent;
import com.example.sign.event.ReviewEvent;
import com.example.sign.review.entity.Review;

public interface ReviewService {

    void escalate(Review review);

    Review findOne(long id);

    void review(long reviewId, ReviewEvent event);

    void reject(long reviewId, RejectEvent event);

}
