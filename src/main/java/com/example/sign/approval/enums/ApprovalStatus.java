package com.example.sign.approval.enums;

public enum ApprovalStatus {

    EMPTY,

    ESCALATED,

    APPROVE_WAIT,

    APPROVED,

    REJECTED,

    CANCELED,;


    public static boolean isEmpty(ApprovalStatus status) {
        return EMPTY.equals(status);
    }
}
