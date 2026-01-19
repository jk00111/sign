package iit.sign.ui.result;

import iit.sign.sign.enums.SignStatus;
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

    public static SignResult proceed(long id, SignStatus status) {
        return new SignResult(id, status);
    }

    public static SignResult rejected(long id) {
        return new SignResult(id, SignStatus.REJECTED);
    }
}
