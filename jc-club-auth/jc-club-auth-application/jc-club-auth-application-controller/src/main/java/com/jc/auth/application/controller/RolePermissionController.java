package com.jc.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jc.auth.application.convert.AuthPermissionDTOConverter;
import com.jc.auth.application.convert.AuthRolePermissionDTOConverter;
import com.jc.auth.application.dto.AuthPermissionDTO;
import com.jc.auth.application.dto.AuthRolePermissionDTO;
import com.jc.auth.common.entity.Result;
import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.domain.entity.AuthRoleBO;
import com.jc.auth.domain.entity.AuthRolePermissionBO;
import com.jc.auth.domain.service.impl.AuthPermissionDomainServiceImpl;
import com.jc.auth.domain.service.impl.AuthRolePermissionDomainServiceImpl;
import com.jc.auth.infra.basic.entity.AuthRolePermission;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @Description 角色权限Controller
 * @Author 12919
 * @Date 2024/11/22
 */
@RestController
@RequestMapping("/rolePermission/")
@Slf4j
public class RolePermissionController {

    @Resource
    private AuthPermissionDomainServiceImpl authPermissionDomainService;

    @Resource
    private AuthRolePermissionDomainServiceImpl authRolePermissionDomainService;

    /**
     * add
     *
     * @param authRolePermissionDTO
     * @return
     */
    @RequestMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("RolePermissionController.add.dto.{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()), "权限关联不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(), "角色id不能为空");
            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.convertDTOToBORolePermission(authRolePermissionDTO);
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        } catch (Exception e) {
            log.error("RolePermissionController.add.error.{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


}
