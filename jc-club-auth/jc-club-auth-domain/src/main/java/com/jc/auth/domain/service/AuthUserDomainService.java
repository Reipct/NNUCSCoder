package com.jc.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.jc.auth.domain.entity.AuthUserBO;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

public interface AuthUserDomainService {

    /**
     * 注册
     * @param authUserBO
     */
    boolean register(AuthUserBO authUserBO);

    SaTokenInfo doLogin(String validCode);
}
