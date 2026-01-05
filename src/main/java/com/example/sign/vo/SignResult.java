package com.example.sign.vo;

import com.example.sign.approval.ApprovalResult;
import com.example.sign.sign.enums.SignStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignResult {

    private final long id;

    private final SignStatus status;


    public static SignResult escalated(long id) {
        return new SignResult(id, SignStatus.ESCALATED);
    }

    public static SignResult canceled(long id) {
        return new SignResult(id, SignStatus.CANCELED);
    }

    public static SignResult approved(long id, ApprovalResult approvalResult) {
        return new SignResult(id, approvalResult.isApproved() ? SignStatus.APPROVED : SignStatus.ESCALATED);
    }

    public static SignResult rejected(long id) {
        return new SignResult(id, SignStatus.REJECTED);
    }
}
