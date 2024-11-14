package com.jc.subject.domain.handler.subject;

import com.jc.subject.common.enums.IsDeleteFlagEnum;
import com.jc.subject.common.enums.SubjectInfoTypeEnum;
import com.jc.subject.domain.entity.SubjectAnswerBO;
import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;
import com.jc.subject.infra.basic.entity.SubjectJudge;
import com.jc.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 判断题目的策略类
 *
 * @Author 12919
 * @Date 2024/11/11
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler {
    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //判断的插入
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        subjectJudgeService.insert(subjectJudge);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        return null;
    }

}
