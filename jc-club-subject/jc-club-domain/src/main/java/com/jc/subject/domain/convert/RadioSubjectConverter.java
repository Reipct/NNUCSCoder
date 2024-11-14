package com.jc.subject.domain.convert;


import com.jc.subject.domain.entity.SubjectAnswerBO;
import com.jc.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RadioSubjectConverter {

    RadioSubjectConverter INSTANCE= Mappers.getMapper(RadioSubjectConverter.class);

    SubjectRadio convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

}
