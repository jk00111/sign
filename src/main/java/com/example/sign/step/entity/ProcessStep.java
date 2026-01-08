package com.example.sign.step.entity;

import com.example.sign.step.enums.StepStatus;

public interface ProcessStep {

    long id();

    StepStatus status();

    void escalate(long processId);

    void proceed(long requesterId);

    void reject(long requesterId);

    boolean isUpdated();

}
