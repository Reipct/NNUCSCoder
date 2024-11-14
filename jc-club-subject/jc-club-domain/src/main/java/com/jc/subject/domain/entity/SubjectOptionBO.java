package com.jc.subject.domain.entity;

import com.jc.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目DTO
 */
@Data
public class SubjectOptionBO  implements Serializable {

    private String subjectAnswer;

    private List<SubjectAnswerBO> optionList;

}
