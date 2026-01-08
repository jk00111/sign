package com.example.sign.submit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewSubmit implements Submit {

    private final long signId;

    private final long requesterId;

}
