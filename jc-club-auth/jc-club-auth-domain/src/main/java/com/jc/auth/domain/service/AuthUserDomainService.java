package com.jc.auth.domain.service;

import com.jc.auth.domain.entity.AuthUserBO;

public interface AuthUserDomainService {

    /**
     * 注册
     * @param authUserBO
     */
    boolean register(AuthUserBO authUserBO);
}
