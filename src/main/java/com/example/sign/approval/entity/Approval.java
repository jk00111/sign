package com.example.sign.approval.entity;

import com.example.sign.approval.enums.ApprovalStatus;
import com.example.sign.step.entity.ApprovalStep;
import com.example.sign.step.entity.ProcessStep;
import com.example.sign.step.enums.StepStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Approval {

    private long signId;
    @Getter
    private long id;
    private ApprovalStatus status;
    private List<ApprovalStep> line;
    private int current;

    private Approval() {
        this.status = ApprovalStatus.EMPTY;
    }

    public Approval(long signId, List<ApprovalStep> line) {
        this.signId = signId;
        this.line = line;
        this.status = ApprovalStatus.ESCALATED;
    }

    public Approval(long signId, long id, ApprovalStatus status, List<ApprovalStep> line) {
        this.signId = signId;
        this.id = id;
        this.status = status;
        this.line = line;
        this.current = current();
    }

    public void escalateLine() {
        line.forEach(step -> step.escalate(this.id));
    }

    public List<ProcessStep> getLine() {
        return new ArrayList<>(this.line);
    }

    public void approve(long userId) {
        ApprovalStep current = getCurrent();
        current.proceed(userId);

        if (hasNext()) {
            ApprovalStep nextStep = line.get(this.current + 1);
            nextStep.waiting();
        }

        if (isFinish()) {
            this.status = ApprovalStatus.APPROVED;
        }
    }

    public void reject(long userId) {
        ApprovalStep current = getCurrent();
        current.reject(userId);

        this.status = ApprovalStatus.REJECTED;
    }

    public List<ProcessStep> getUpdated() {
        return line.stream()
                .filter(ProcessStep::isUpdated)
                .collect(Collectors.toList());
    }

    public void setLine(List<ApprovalStep> line) {
        this.line = line;
    }

    public static Approval empty() {
        return new Approval();
    }

    public boolean isEmpty() {
        return ApprovalStatus.EMPTY.equals(this.status);
    }

    private ApprovalStep getCurrent(){
        return line.get(current);
    }

    public boolean isFinish() {
        return line.stream()
                .allMatch(step -> StepStatus.APPROVED.equals(step.status()));
    }

    public boolean isRejected() {
        return line.stream()
                .anyMatch(step -> StepStatus.REJECTED.equals(step.status()));
    }

    private int current() {
        ApprovalStep current = line.stream()
                .filter(v -> StepStatus.WAITING.equals(v.status()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        return line.indexOf(current);
    }

    private boolean hasNext() {
        return current < line.size() + 1;
    }
}
