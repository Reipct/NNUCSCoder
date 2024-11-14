package com.jc.subject.domain.handler.subject;

import com.jc.subject.common.enums.IsDeleteFlagEnum;
import com.jc.subject.common.enums.SubjectInfoTypeEnum;
import com.jc.subject.domain.convert.BriefSubjectConverter;
import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;
import com.jc.subject.infra.basic.entity.SubjectBrief;
import com.jc.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 简答题目的策略类
 *
 * @Author 12919
 * @Date 2024/11/11
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler {


    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIFE;
    }


    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //简答题的新增
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE.convertBoToEntity(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId().intValue());
        subjectBrief.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectBrief subjectBrief = subjectBriefService.queryById(Long.valueOf(subjectId));
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(subjectBrief.getSubjectAnswer());
        return subjectOptionBO;
    }
}
