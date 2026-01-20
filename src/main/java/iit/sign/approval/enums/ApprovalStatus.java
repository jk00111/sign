package iit.sign.approval.enums;

public enum ApprovalStatus {

    EMPTY,

    ESCALATED,

    WAITING,

    APPROVED,

    REJECTED,

    CANCELED,;


    public static boolean isEmpty(ApprovalStatus status) {
        return EMPTY.equals(status);
    }
}
