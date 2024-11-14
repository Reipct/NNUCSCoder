package com.jc.subject.domain.entity;

import com.jc.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目DTO
 */
@Data
public class SubjectInfoBO extends PageInfo implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 答案
     */
    private String subjectAnswer;
    /**
     * 题目id
     */
    private List<Integer> categoryIds;
    /**
     * 标签id
     */
    private List<Integer> labelIds;


    /**
     * 标签name
     */
    private List<String > labelName;


    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

    /**
     * 题目类别id
     */
    private Long categoryId;
    /**
     * 题目标签id
     */
    private Long labelId;
}

