package com.example.sign.review.enums;

public enum ReviewStatus {

    EMPTY,

    ESCALATED,

    WAITING,

    REVIEWED,

    REJECTED,

    CANCELED,;


    public static boolean isEmpty(ReviewStatus status) {
        return EMPTY.equals(status);
    }

}
