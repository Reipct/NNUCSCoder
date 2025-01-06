package com.jc.auth.domain.service;

import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.domain.entity.AuthRoleBO;

import java.util.List;

public interface AuthPermissionDomainService {

    /**
     * 注册
     * @param authPermissionBO
     */
    boolean add(AuthPermissionBO authPermissionBO);

    /**
     * 更新
     * @param authPermissionBO
     */
    boolean update(AuthPermissionBO authPermissionBO);

    /**
     * 删除
     * @param authPermissionBO
     */
    boolean delete(AuthPermissionBO authPermissionBO);


    /**
     * 查询用户权限
     * @param userName
     */
    List<String> getPermission(String userName);
}
