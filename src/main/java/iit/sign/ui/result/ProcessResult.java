package iit.sign.ui.result;

import iit.sign.approval.enums.ApprovalStatus;
import iit.sign.review.enums.ReviewStatus;
import iit.sign.sign.enums.SignStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProcessResult {
    
    private final SignStatus status;

    public static ProcessResult fromApproval(ApprovalStatus status) {
        if (ApprovalStatus.APPROVED.equals(status)) {
            return new ProcessResult(SignStatus.APPROVED);
        }

        if (ApprovalStatus.REJECTED.equals(status)) {
            return new ProcessResult(SignStatus.REJECTED);
        }

        return new ProcessResult(SignStatus.APPROVING);
    }

    public static ProcessResult fromReview(ReviewStatus status) {
        if (ReviewStatus.REVIEWED.equals(status)) {
            return new ProcessResult(SignStatus.REVIEWED);
        }

        if (ReviewStatus.REJECTED.equals(status)) {
           return new ProcessResult(SignStatus.REJECTED);
        }

        return new ProcessResult(SignStatus.REVIEWING);
    }
}
