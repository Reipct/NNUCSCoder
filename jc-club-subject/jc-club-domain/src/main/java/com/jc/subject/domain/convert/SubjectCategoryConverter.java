package com.jc.subject.domain.convert;


import com.jc.subject.domain.entity.SubjectCategoryBO;
import com.jc.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryConverter {
    SubjectCategoryConverter INSTANCE = Mappers.getMapper(SubjectCategoryConverter.class);

    /**
     * Category:BO->Entity
     *
     * @param subjectCategoryBO
     * @return
     */
    SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * CategoryList:BO->Entity
     *
     * @param categoryList
     * @return
     */
    List<SubjectCategoryBO> convertBoToCategory(List<SubjectCategory> categoryList);
}
