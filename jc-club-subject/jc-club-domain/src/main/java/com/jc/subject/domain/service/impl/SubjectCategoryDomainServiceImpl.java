package com.jc.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jc.subject.common.enums.IsDeleteFlagEnum;
import com.jc.subject.domain.convert.SubjectCategoryConverter;
import com.jc.subject.domain.entity.SubjectCategoryBO;
import com.jc.subject.domain.entity.SubjectLabelBO;
import com.jc.subject.domain.service.SubjectCategoryDomainService;
import com.jc.subject.infra.basic.entity.SubjectCategory;
import com.jc.subject.infra.basic.entity.SubjectLabel;
import com.jc.subject.infra.basic.entity.SubjectMapping;
import com.jc.subject.infra.basic.service.SubjectCategoryService;
import com.jc.subject.infra.basic.service.SubjectLabelService;
import com.jc.subject.infra.basic.service.SubjectMappingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private ThreadPoolExecutor labelThreadPool;

    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.bo.{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted((IsDeleteFlagEnum.UN_DELETED.getCode()));
        subjectCategoryService.insert(subjectCategory);
    }


    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);

        subjectCategory.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());

        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> boList = SubjectCategoryConverter.INSTANCE
                .convertCategoryToBo(subjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryPrimaryCategory.boList.{}", JSON.toJSONString(boList));
        }
        boList.forEach(bo -> {
            Integer SubjectCount = subjectCategoryService.querySubjectCount(bo.getId());
            bo.setCount(SubjectCount);
        });
        return boList;
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @SneakyThrows
    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        //查询大类下所有分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(subjectCategoryBO.getId());
        subjectCategory.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryCategoryAndLabel.subjectCategoryList.{}",
                    JSON.toJSONString(subjectCategoryList));
        }
        List<SubjectCategoryBO> CategoryBOList = SubjectCategoryConverter.INSTANCE.convertCategoryToBo(subjectCategoryList);
        //一次性获得标签信息
        List<FutureTask<Map<Long, List<SubjectLabelBO>>>> futureTaskList = new LinkedList<>();
        //线程池并发调用
        Map<Long, List<SubjectLabelBO>> map = new HashMap<>();
        CategoryBOList.forEach(category -> {
            FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask = new FutureTask<>(() ->
                    getLabelBOSList(category));
            futureTaskList.add(futureTask);
            labelThreadPool.submit(futureTask);
        });

        for (FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask : futureTaskList) {
            Map<Long, List<SubjectLabelBO>> resultMap = futureTask.get();
            if (CollectionUtils.isEmpty(resultMap))
                continue;
            map.putAll(resultMap);
        }
        CategoryBOList.forEach(subjectCategoryBO1 -> {
            subjectCategoryBO.setLabelBOList(map.get(subjectCategoryBO.getId()));
        });
        return CategoryBOList;
    }


    private Map<Long, List<SubjectLabelBO>> getLabelBOSList(SubjectCategoryBO category) {
        Map<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(category.getId());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList))
            return null;
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> LabelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> labelBOList = new LinkedList<>();
        LabelList.forEach(label -> {
            SubjectLabelBO subjectLabelBO = new SubjectLabelBO();
            subjectLabelBO.setId(label.getId());
            subjectLabelBO.setLabelName(label.getLabelName());
            subjectLabelBO.setCategoryId(label.getCategoryId());
            subjectLabelBO.setSortNum(label.getSortNum());
            labelBOList.add(subjectLabelBO);

        });

        labelMap.put(category.getId(), labelBOList);
        return labelMap;
    }

}
