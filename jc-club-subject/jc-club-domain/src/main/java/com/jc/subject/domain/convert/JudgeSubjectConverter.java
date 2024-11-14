package com.jc.subject.domain.convert;


import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface JudgeSubjectConverter {

    JudgeSubjectConverter INSTANCE= Mappers.getMapper(JudgeSubjectConverter.class);

    SubjectBrief convertBoToEntity(SubjectInfoBO subjectInfoBO);

}
