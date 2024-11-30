package com.jc.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.jc.auth.common.enums.IsDeleteFlagEnum;
import com.jc.auth.domain.convert.AuthPermissionBOConverter;
import com.jc.auth.domain.convert.AuthRolePermissionBOConverter;
import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.domain.entity.AuthRolePermissionBO;
import com.jc.auth.domain.service.AuthPermissionDomainService;
import com.jc.auth.domain.service.AuthRolePermissionDomainService;
import com.jc.auth.infra.basic.entity.AuthPermission;
import com.jc.auth.infra.basic.entity.AuthRolePermission;
import com.jc.auth.infra.basic.service.AuthPermissionService;
import com.jc.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;


    @Override
    public boolean add(AuthRolePermissionBO authRolePermissionBO) {
        if (log.isInfoEnabled()) {
            log.info("AuthRolePermissionDomainServiceImpl.add.bo.{}", JSON.toJSONString(authRolePermissionBO));
        }
        List<AuthRolePermission> rolePermissionIdList = new LinkedList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
            rolePermissionIdList.add(authRolePermission);
        });
        int count = authRolePermissionService.batchInsert(rolePermissionIdList);
        return count > 0;
    }


}
