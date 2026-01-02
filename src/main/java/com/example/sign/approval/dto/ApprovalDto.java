package com.example.sign.approval.dto;

import com.example.sign.document.dto.Cancel;
import com.example.sign.line.entity.ApprovalStep;
import lombok.Getter;

import java.util.List;

public class ApprovalDto {

    @Getter
    private Cancel cancel;
    private List<ApprovalStep> steps;

    public List<ApprovalStep> steps() {
        return this.steps;
    }
}
