package com.example.sign.sign.repository;

import com.example.sign.sign.entity.Sign;
import org.springframework.stereotype.Repository;

@Repository
public interface SignRepository {

    Sign findOne(long id);

    void create(Sign sign);

    void update(Sign sign);

}
