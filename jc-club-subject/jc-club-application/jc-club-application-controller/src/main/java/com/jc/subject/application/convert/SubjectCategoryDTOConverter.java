package com.jc.subject.application.convert;


import com.jc.subject.application.dto.SubjectCategoryDTO;
import com.jc.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE= Mappers.getMapper(SubjectCategoryDTOConverter.class);

//    /**
//     * Category:DTO->BO
//     * @param subjectCategoryDTO
//     * @return
//     */
//    SubjectCategoryBO convertBoToCategory(SubjectCategoryDTO subjectCategoryDTO);
    /**
     * CategoryList:BO->DTO
     * @param subjectCategoryBOList
     * @return
     */
    List<SubjectCategoryDTO> convertBoToCategoryDTOList(List<SubjectCategoryBO> subjectCategoryBOList);
    /**
     * Category:DTO->BO
     * @param subjectCategoryDTO
     * @return
     */
    SubjectCategoryBO convertDTOToCategoryBO(SubjectCategoryDTO subjectCategoryDTO);

}
