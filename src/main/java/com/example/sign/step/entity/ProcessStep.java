package com.example.sign.step.entity;

import com.example.sign.step.enums.StepStatus;

public interface ProcessStep {

    long id();

    void proceed(long requesterId);

    void reject(long requesterId);

    StepStatus status();

    boolean isUpdated();

    void escalate(long processId);

}
