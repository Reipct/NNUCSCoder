package com.jc.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jc.auth.common.enums.IsDeleteFlagEnum;
import com.jc.auth.domain.convert.AuthPermissionBOConverter;
import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.domain.service.AuthPermissionDomainService;
import com.jc.auth.infra.basic.entity.AuthPermission;
import com.jc.auth.infra.basic.service.AuthPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;


    @Override
    public boolean add(AuthPermissionBO authPermissionBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthPermissionDomainServiceImpl.add.bo.{}", JSON.toJSONString(authPermissionBO));
        }
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.convertBOToEntityPermission(authPermissionBO);
        authPermission.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        int count = authPermissionService.insert(authPermission);
        return count > 0;
    }


    @Override
    public boolean update(AuthPermissionBO authPermissionBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthPermissionDomainServiceImpl.update.bo.{}", JSON.toJSONString(authPermissionBO));
        }
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE
                .convertBOToEntityPermission(authPermissionBO);
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }

    @Override
    public boolean delete(AuthPermissionBO authPermissionBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthPermissionDomainServiceImpl.delete.bo.{}", JSON.toJSONString(authPermissionBO));
        }
        AuthPermission authPermission=new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }
}
