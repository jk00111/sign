package com.example.sign.line.entity;

public interface ApprovalStep extends ProcessStep {

    void waiting();

    void pass();
}
