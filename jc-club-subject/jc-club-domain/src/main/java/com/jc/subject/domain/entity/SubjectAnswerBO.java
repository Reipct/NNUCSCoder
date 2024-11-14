package com.jc.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案DTO
 */
@Data
public class SubjectAnswerBO implements Serializable {

    /**
     * 答案选项
     */
    private Integer optionType;
    /**
     * 答案
     */
    private String optionContent;
    /**
     * 对错
     */
    private Integer isCorrect;

}

