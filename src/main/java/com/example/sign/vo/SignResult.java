package com.example.sign.vo;

import com.example.sign.result.Result;
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

    public static SignResult approved(long id, Result result) {
        return new SignResult(id, result.isApproved() ? SignStatus.APPROVED : SignStatus.ESCALATED);
    }

    public static SignResult rejected(long id) {
        return new SignResult(id, SignStatus.REJECTED);
    }
}
