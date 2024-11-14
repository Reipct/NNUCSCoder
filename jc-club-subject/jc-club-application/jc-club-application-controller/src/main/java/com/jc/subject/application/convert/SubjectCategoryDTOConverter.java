package com.jc.subject.application.convert;


import com.jc.subject.application.dto.SubjectCategoryDTO;
import com.jc.subject.application.dto.SubjectLabelDTO;
import com.jc.subject.domain.entity.SubjectCategoryBO;
import com.jc.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE= Mappers.getMapper(SubjectCategoryDTOConverter.class);
    SubjectCategoryBO convertBoToCategory(SubjectCategoryDTO subjectCategoryDTO);
    List<SubjectCategoryDTO> convertBoToCategoryDTOList(List<SubjectCategoryBO> subjectCategoryBOList);

    SubjectCategoryBO convertDTOToCategoryBO(SubjectCategoryDTO subjectCategoryDTO);

}
