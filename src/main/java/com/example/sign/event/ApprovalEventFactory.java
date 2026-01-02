package com.example.sign.event;

import com.example.sign.event.enums.EventType;

public class ApprovalEventFactory {

    public static ApprovalEvent ofApprove(boolean isApproved) {
        if (isApproved) {
            return new ApprovalEvent(EventType.APPROVED);
        }

        return ofDefault();
    }

    public static ApprovalEvent ofReview(boolean isFinish) {
        if (isFinish) {
            return new ApprovalEvent(EventType.REVIEWED);
        }

        return ofDefault();
    }

    public static ApprovalEvent ofReject() {
        return new ApprovalEvent(EventType.REJECTED);
    }


    private static ApprovalEvent ofDefault() {
        return new ApprovalEvent(EventType.NONE);
    }
}
