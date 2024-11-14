package com.jc.subject.domain.service;

import com.jc.subject.common.entity.PageResult;
import com.jc.subject.domain.entity.SubjectInfoBO;


public interface SubjectInfoDomainService {

    /**
     * 新增题目
     * @param subjectInfoBO
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 分页查询
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);
}
