package com.jc.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jc.subject.common.entity.PageResult;
import com.jc.subject.common.enums.IsDeleteFlagEnum;
import com.jc.subject.domain.convert.SubjectInfoConverter;
import com.jc.subject.domain.entity.SubjectInfoBO;
import com.jc.subject.domain.entity.SubjectOptionBO;
import com.jc.subject.domain.handler.subject.SubjectTypeHandler;
import com.jc.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.jc.subject.domain.service.SubjectInfoDomainService;
import com.jc.subject.infra.basic.entity.SubjectInfo;
import com.jc.subject.infra.basic.entity.SubjectMapping;
import com.jc.subject.infra.basic.service.SubjectInfoService;
import com.jc.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Slf4j
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo.{}", JSON.toJSONString(subjectInfoBO));
        }
        //工厂
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        //
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> mappingList = new LinkedList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
                mappingList.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(mappingList);
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);

        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId());
        if (count == 0) {
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.convertListInfoToBo(subjectInfoList);
        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        Integer subjectType = subjectInfo.getSubjectType();
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBO OptionBO = handler.query(subjectInfo.getId().intValue());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBO(OptionBO, subjectInfo);
        List<String> labelNameList = new ArrayList<>();
        bo.setLabelName(labelNameList);
        return bo;
    }

}
