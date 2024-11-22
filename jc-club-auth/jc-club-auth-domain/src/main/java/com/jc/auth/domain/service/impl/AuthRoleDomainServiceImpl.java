package com.jc.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jc.auth.common.enums.IsDeleteFlagEnum;
import com.jc.auth.domain.convert.AuthRoleBOConverter;
import com.jc.auth.domain.entity.AuthRoleBO;
import com.jc.auth.domain.service.AuthRoleDomainService;
import com.jc.auth.infra.basic.entity.AuthRole;
import com.jc.auth.infra.basic.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    @Override
    public boolean add(AuthRoleBO authRoleBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthRoleDomainServiceImpl.add.bo.{}", JSON.toJSONString(authRoleBO));
        }
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.convertBOToEntityRole(authRoleBO);
        authRole.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        int count = authRoleService.insert(authRole);
        return count > 0;
    }

    @Override
    public boolean update(AuthRoleBO authRoleBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthRoleDomainServiceImpl.update.bo.{}", JSON.toJSONString(authRoleBO));
        }
        AuthRole authRole = AuthRoleBOConverter.INSTANCE
                .convertBOToEntityRole(authRoleBO);
        int count = authRoleService.update(authRole);
        return count > 0;
    }

    @Override
    public boolean delete(AuthRoleBO authRoleBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthRoleDomainServiceImpl.delete.bo.{}", JSON.toJSONString(authRoleBO));
        }
        AuthRole authRole=new AuthRole();
        authRole.setId(authRoleBO.getId());
        authRole.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        int count = authRoleService.update(authRole);
        return count > 0;
    }

}
