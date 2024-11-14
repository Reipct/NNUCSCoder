package com.jc.subject.domain.convert;


import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;
import com.jc.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {
    SubjectInfoConverter INSTANCE= Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBoToInfo(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO convertOptionToBO(SubjectOptionBO subjectOptionBO);

    SubjectInfoBO convertOptionAndInfoToBO(SubjectOptionBO subjectOptionBO,SubjectInfo subjectInfo);
    List<SubjectInfoBO> convertListInfoToBo(List<SubjectInfo> subjectInfoList);
}
