package com.jc.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jc.auth.application.convert.AuthPermissionDTOConverter;
import com.jc.auth.application.dto.AuthPermissionDTO;
import com.jc.auth.common.entity.Result;
import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.domain.service.impl.AuthPermissionDomainServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/22
 */
@RestController
@RequestMapping("/permission/")
@Slf4j
public class PermissionController {

    @Resource
    private AuthPermissionDomainServiceImpl authPermissionDomainService;

    /**
     * add
     *
     * @param authPermissionDTO
     * @return
     */
    @RequestMapping("/add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.add.dto.{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()), "权限名称不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(), "权限父id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBOPermission(authPermissionDTO);
            return Result.ok(authPermissionDomainService.add(authPermissionBO));
        } catch (Exception e) {
            log.error("RoleController.add.error.{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 更新角色
     *
     * @param authPermissionDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.update.dto.{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBOPermission(authPermissionDTO);
            return Result.ok(authPermissionDomainService.update(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.update.error.{}", e.getMessage(), e);
            return Result.fail("更新角色失败");
        }
    }


    /**
     * delete
     *
     * @param authPermissionDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("PermissionController.delete.dto.{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(), "权限id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.convertDTOToBOPermission(authPermissionDTO);
            return Result.ok(authPermissionDomainService.delete(authPermissionBO));
        } catch (Exception e) {
            log.error("PermissionController.delete.error.{}", e.getMessage(), e);
            return Result.fail("删除角色失败");
        }
    }

}
