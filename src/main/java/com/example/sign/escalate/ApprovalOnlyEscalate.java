package com.example.sign.escalate;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ApprovalOnlyEscalate implements Escalate {

    private final Escalater escalater;

    private final Approvals approvals;

    private final Reviews reviews = Reviews.empty();

}
