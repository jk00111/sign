package com.example.sign.document.entity;

import com.example.sign.document.dto.Cancel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Sign {

    private long id;
    private long escalaterId;

    public void update(Cancel dto) {

    }

    public void cancel(Cancel dto) {
        if (!validateRequester(dto.getRequester().getId())) {
            throw new IllegalArgumentException();
        }
    }

    private boolean validateRequester(long userId) {
        return this.escalaterId == userId;
    }
}
