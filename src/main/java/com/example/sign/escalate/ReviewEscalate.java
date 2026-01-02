package com.example.sign.escalate;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ReviewEscalate implements Escalate {

    private final Escalater escalater;

    private final Approvals approvals = Approvals.empty();

    private final Reviews reviews;

}
