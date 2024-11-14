package com.jc.subject.domain.handler.subject;

import com.jc.subject.common.enums.IsDeleteFlagEnum;
import com.jc.subject.common.enums.SubjectInfoTypeEnum;
import com.jc.subject.domain.convert.MultipleSubjectConverter;
import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;
import com.jc.subject.infra.basic.entity.SubjectMultiple;
import com.jc.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 多选题目的策略类
 *
 * @Author 12919
 * @Date 2024/11/11
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //多选题目的插入
        List<SubjectMultiple> subjectMultipleList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
                    SubjectMultiple subjectMultiple = MultipleSubjectConverter.INSTANCE.convertBoToEntity(option);
                    subjectMultiple.setSubjectId(subjectInfoBO.getId());
                    subjectMultiple.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
                    subjectMultipleList.add(subjectMultiple);
                }
        );
        subjectMultipleService.batchInsert(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(int subjectId) {
        return null;
    }

}
