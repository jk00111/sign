package iit.sign.step.entity;

import iit.sign.step.enums.StepStatus;

public interface ProcessStep {

    long id();

    StepStatus status();

    void setProcessId(long processId);

    void proceed(long requesterId);

    void reject(long requesterId);

    boolean isUpdated();

}
