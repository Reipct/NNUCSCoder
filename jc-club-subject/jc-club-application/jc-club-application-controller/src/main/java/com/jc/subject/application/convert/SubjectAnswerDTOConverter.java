package com.jc.subject.application.convert;


import com.jc.subject.application.dto.SubjectAnswerDTO;
import com.jc.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    /**
     * Answer：DTO->BO
     *
     * @param subjectAnswerDTO
     * @return
     */
    SubjectAnswerBO convertDTOToBO(SubjectAnswerDTO subjectAnswerDTO);

    /**
     * AnswerList：DTO->BO
     *
     * @param subjectAnswerDTO
     * @return
     */
    List<SubjectAnswerBO> convertListDTOToBO(List<SubjectAnswerDTO> subjectAnswerDTO);
}
