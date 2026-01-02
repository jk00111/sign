package com.example.sign.document.repository;

import com.example.sign.document.entity.Sign;
import org.springframework.stereotype.Repository;

@Repository
public interface SignRepository {

    Sign findOne(long id);

    void create(Sign sign);

    void update(Sign sign);

}
