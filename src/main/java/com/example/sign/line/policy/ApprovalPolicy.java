package com.example.sign.line.policy;

import com.example.sign.line.entity.ApprovalLine;
import com.example.sign.line.entity.ApprovalStep;
import com.example.sign.vo.ApprovalUser;

public interface ApprovalPolicy {

    ApprovalStep apply(ApprovalLine line, ApprovalUser user);

}
