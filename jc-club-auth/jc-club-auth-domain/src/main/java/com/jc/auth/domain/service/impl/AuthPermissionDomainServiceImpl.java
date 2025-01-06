package com.jc.auth.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jc.auth.common.enums.IsDeleteFlagEnum;
import com.jc.auth.domain.convert.AuthPermissionBOConverter;
import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.domain.redis.RedisUtil;
import com.jc.auth.domain.service.AuthPermissionDomainService;
import com.jc.auth.infra.basic.entity.AuthPermission;
import com.jc.auth.infra.basic.service.AuthPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

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
        AuthPermission authPermission = new AuthPermission();
        authPermission.setId(authPermissionBO.getId());
        authPermission.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        int count = authPermissionService.update(authPermission);
        return count > 0;
    }


    @Override
    public List<String> getPermission(String userName) {
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, userName);
        String permissionValue = redisUtil.get(permissionKey);
        if (StringUtils.isBlank(permissionValue)) {
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = new Gson().fromJson(permissionValue, new TypeToken<List<AuthPermission>>() {
        }.getType());
        List<String> authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        return authList;
    }
}
