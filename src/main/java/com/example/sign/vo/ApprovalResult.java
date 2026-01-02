package com.example.sign.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApprovalResult {

    private final long id;

    private final boolean isFinish;


    public static ApprovalResult escalated(long id) {
        return new ApprovalResult(id, false);
    }

    public static ApprovalResult canceled(long id) {
        return new ApprovalResult(id, true);
    }
}
