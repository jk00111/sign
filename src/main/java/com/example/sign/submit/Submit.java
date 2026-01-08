package com.example.sign.submit;

public interface Submit {

    long getSignId();

    long getRequesterId();


    static ApprovalSubmit ofApproval(long signId, long requesterId) {
        return new ApprovalSubmit(signId, requesterId);
    }

    static ReviewSubmit ofReview(long signId, long requesterId) {
        return new ReviewSubmit(signId, requesterId);
    }

    static CancelSubmit ofCancel(long signId, long requesterId) {
        return new CancelSubmit(signId, requesterId);
    }
}
