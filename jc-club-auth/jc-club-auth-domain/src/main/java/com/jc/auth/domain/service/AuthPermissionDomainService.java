package com.jc.auth.domain.service;

import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.domain.entity.AuthRoleBO;

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
}
