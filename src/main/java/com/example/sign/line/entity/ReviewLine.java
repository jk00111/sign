package com.example.sign.line.entity;

import com.example.sign.line.enums.StepStatus;
import com.example.sign.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.Set;

@RequiredArgsConstructor
public class ReviewLine implements Iterable<ReviewStep> {

    private final Set<ReviewStep> steps;

    public ReviewLine(long id, Set<ReviewStep> steps) {
        this.steps = steps;
    }

    @Override
    public Iterator<ReviewStep> iterator() {
        return steps.iterator();
    }

    public ReviewStep get(ApprovalUser user) {
        return steps.stream()
                .filter(step -> step.id() == user.getId())
                .findFirst().orElseThrow();
    }

    public boolean isReviewed() {
        return steps.stream()
                .allMatch(step -> StepStatus.REVIEWED.equals(step.status()));
    }
}
