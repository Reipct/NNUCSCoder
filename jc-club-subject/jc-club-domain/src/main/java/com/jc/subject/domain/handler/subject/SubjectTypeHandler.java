package com.jc.subject.domain.handler.subject;

import com.jc.subject.common.enums.SubjectInfoTypeEnum;
import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;
import org.springframework.stereotype.Component;

@Component
public interface SubjectTypeHandler {
    /**
     * 枚举身份识别
     *
     * @return
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际题目插入
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 实际题目插入
     */
    SubjectOptionBO query(int subjectId);
}
