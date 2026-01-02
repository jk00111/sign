package com.example.sign.line.service;

import com.example.sign.event.ApprovalEvent;
import com.example.sign.event.ApprovalEventFactory;
import com.example.sign.line.entity.*;
import com.example.sign.line.repository.StepRepository;
import com.example.sign.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class LineServiceImpl implements LineService {

    private final StepRepository repository;

    @Override
    public ProcessStep findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<ApprovalStep> findByApproval(long approvalId) {
        return repository.findByApproval(approvalId);
    }

    @Override
    public Set<ReviewStep> findByReview(long reviewId) {
        return repository.findByReview(reviewId);
    }

    @Override
    public void create(List<ApprovalStep> line) {
        line.forEach(repository::create);
    }

    @Override
    public void create(Set<ReviewStep> line) {
        line.forEach(repository::create);
    }

    @Override
    public ApprovalEvent approve(ApprovalLine line, ApprovalUser user) {
        line.approve(user);
        List<ApprovalStep> updated = line.getUpdated();
        updated.forEach(repository::update);

        return ApprovalEventFactory.ofApprove(line.isApproved());
    }


    /**
    *  디미토 법칙 -> 라인 서비스가 step의 내부구현을 알아야 할 필요가 있는가??
    *  라인이 전달 역할만 한다면 라인 서비스의 역할은 머임?
    *  업데이트 과정떄문에 스탭 반환은 필요함
    *
    * */
    @Override
    public ApprovalEvent reject(ApprovalLine line, ApprovalUser user) {
        ProcessStep current = line.getCurrent();
        current.reject(user);
        repository.update(current);
        return ApprovalEventFactory.ofReject();
    }

    @Override
    public ApprovalEvent review(ReviewLine line, ApprovalUser reviewer) {
        ProcessStep reviewStep = line.get(reviewer);
        reviewStep.proceed(reviewer);
        repository.update(reviewStep);
        return ApprovalEventFactory.ofApprove(line.isReviewed());
    }

    @Override
    public ApprovalEvent reject(ReviewLine line, ApprovalUser user) {
        ReviewStep reviewStep = line.get(user);
        reviewStep.reject(user);
        repository.update(reviewStep);
        return ApprovalEventFactory.ofReject();
    }

    private void activateNext(ApprovalLine line) {
        ApprovalStep next = line.next();
        next.waiting();
        repository.update(next);
    }
}
