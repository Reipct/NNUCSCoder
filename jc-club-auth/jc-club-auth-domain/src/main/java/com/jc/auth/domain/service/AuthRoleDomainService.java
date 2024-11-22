package com.jc.auth.domain.service;

import com.jc.auth.domain.entity.AuthRoleBO;

public interface AuthRoleDomainService {

    /**
     * 注册
     * @param authRoleBO
     */
    boolean add(AuthRoleBO authRoleBO);
    /**
     * 更新
     * @param authRoleBO
     */
    boolean update(AuthRoleBO authRoleBO);
    /**
     * 删除
     * @param authRoleBO
     */
    boolean delete(AuthRoleBO authRoleBO);

}
