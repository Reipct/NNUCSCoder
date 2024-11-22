package com.jc.auth.domain.service.impl;

import com.jc.auth.common.enums.AuthUserStatusEnum;
import com.jc.auth.common.enums.IsDeleteFlagEnum;
import com.jc.auth.domain.convert.AuthUserBOConverter;
import com.jc.auth.domain.entity.AuthUserBO;
import com.jc.auth.domain.service.AuthUserDomainService;
import com.jc.auth.infra.basic.entity.AuthUser;
import com.jc.auth.infra.basic.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    @Resource
    private AuthUserService authUserService;

    @Override
    public boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntityAuth(authUserBO);
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        //建立一个初步的角色的关联
        //把当前用户的角色和权限都刷到redis里
        return count > 0;
    }
}
