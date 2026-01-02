package com.example.sign.review.dto;

import com.example.sign.document.dto.Cancel;
import com.example.sign.line.entity.ReviewStep;
import lombok.Getter;

import java.util.Set;

public class ReviewDto {

    @Getter
    private Cancel cancel;
    private Set<ReviewStep> steps;

    public Set<ReviewStep> steps(){
        return this.steps;
    }
}
