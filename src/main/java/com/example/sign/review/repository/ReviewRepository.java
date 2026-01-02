package com.example.sign.review.repository;

import com.example.sign.review.entity.Review;

public interface ReviewRepository {

    Review findOne(long id);

    void create(Review review);

    void update(Review review);

}
