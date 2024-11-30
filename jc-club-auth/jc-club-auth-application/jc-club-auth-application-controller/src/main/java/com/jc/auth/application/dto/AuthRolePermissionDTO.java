package com.jc.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)DTO类
 *
 * @author makejava
 * @since 2024-11-28 15:15:53
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = -48736432377882537L;
    
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;

    private List<Long> permissionIdList;


}

