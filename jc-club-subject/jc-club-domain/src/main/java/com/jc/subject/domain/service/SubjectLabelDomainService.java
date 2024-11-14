package com.jc.subject.domain.service;

import com.jc.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * 题目标签领域服务
 */
public interface SubjectLabelDomainService {

    /**
     * 新增分类
     *
     * @param subjectLabelBO
     */
    Boolean add(SubjectLabelBO subjectLabelBO);

    /**
     * 更新标签
     * @param subjectLabelBO
     * @return
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     * @param subjectLabelBO
     * @return
     */
    Boolean delete(SubjectLabelBO subjectLabelBO);

    /**
     * 查找分类下标签
     * @param subjectLabelBO
     * @return
     */
    List<SubjectLabelBO> queryLabelBycategoryId(SubjectLabelBO subjectLabelBO);

}
