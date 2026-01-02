package com.example.sign.line.service;

import com.example.sign.event.ApprovalEvent;
import com.example.sign.line.entity.*;
import com.example.sign.vo.ApprovalUser;

import java.util.List;
import java.util.Set;

public interface LineService {

    ProcessStep findOne(long id);

    List<ApprovalStep> findByApproval(long approvalId);

    Set<ReviewStep> findByReview(long reviewId);

    void create(List<ApprovalStep> line);

    void create(Set<ReviewStep> line);

    ApprovalEvent approve(ApprovalLine line, ApprovalUser user);

    ApprovalEvent reject(ApprovalLine line, ApprovalUser user);

    ApprovalEvent review(ReviewLine line, ApprovalUser user);

    ApprovalEvent reject(ReviewLine line, ApprovalUser user);

}
