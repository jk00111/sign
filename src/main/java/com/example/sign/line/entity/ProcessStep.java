package com.example.sign.line.entity;

import com.example.sign.line.enums.StepStatus;
import com.example.sign.vo.ApprovalUser;

public interface ProcessStep {

    long id();

    void proceed(ApprovalUser user);

    void reject(ApprovalUser user);

    StepStatus status();

    boolean isUpdated();
}
