package com.jc.subject.application.convert;


import com.jc.subject.application.dto.SubjectLabelDTO;
import com.jc.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * 标签DTO转换
 */
@Mapper
public interface SubjectLabelDTOConverter {
    SubjectLabelDTOConverter INSTANCE= Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDTOToLabelBO(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBOTOLabelDTOList(List<SubjectLabelBO> subjectLabelBOList);


}