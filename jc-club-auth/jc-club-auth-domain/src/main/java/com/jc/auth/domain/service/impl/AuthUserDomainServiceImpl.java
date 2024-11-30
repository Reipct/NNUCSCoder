package com.jc.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import com.jc.auth.common.enums.AuthUserStatusEnum;
import com.jc.auth.common.enums.IsDeleteFlagEnum;
import com.jc.auth.domain.constants.AuthConstant;
import com.jc.auth.domain.convert.AuthUserBOConverter;
import com.jc.auth.domain.entity.AuthUserBO;
import com.jc.auth.domain.redis.RedisUtil;
import com.jc.auth.domain.service.AuthUserDomainService;
import com.jc.auth.infra.basic.entity.*;
import com.jc.auth.infra.basic.service.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {
    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthRoleService authRoleService;
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private AuthRolePermissionService authRolePermissionService;
    private String salt = "NNUCS";

    @Resource
    private RedisUtil redisUtil;

    private static final String LOGIN_PREFIX = "loginCode";

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntityAuth(authUserBO);
        if (StringUtils.isNotBlank(authUser.getPassword()))
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));

        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);

        //建立一个初步的角色的关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole authRoleresult = authRoleService.queryByCondition(authRole);
        Long roleId = authRoleresult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);

        //把当前用户的角色和权限都刷到redis里
        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey, new Gson().toJson(roleList));

        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleId);
        List<AuthRolePermission> rolePermissionList = authRolePermissionService.
                queryByCondition(authRolePermission);

        List<Long> permissionIdList = rolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());

        //根据roleId查权限
        List<AuthPermission> permissionList = authPermissionService.queryByRoleList(permissionIdList);

        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(permissionList));

        return count > 0;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        String loginKey = redisUtil.buildKey(LOGIN_PREFIX, validCode);
        String openId = redisUtil.get(loginKey);
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        this.register((authUserBO));
        StpUtil.login(openId);
        SaTokenInfo tokenInfo=StpUtil.getTokenInfo();
        return tokenInfo;
    }

}
