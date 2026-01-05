package com.example.sign.approval.submit;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Submit {

    private long signId;

    private long requesterId;

}
