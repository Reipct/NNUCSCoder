package com.jc.subject.application.convert;

import com.jc.subject.application.dto.SubjectInfoDTO;
import com.jc.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface SubjectInfoDTOConverter {
    SubjectInfoDTOConverter INSTANCE= Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTOToBO(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBOToDTO(SubjectInfoBO subjectInfoBO);

}
