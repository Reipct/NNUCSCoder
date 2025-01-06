package com.jc.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.jc.auth.domain.entity.AuthUserBO;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthUserDomainService {

    boolean register(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);

    AuthUserBO getUserInfo(AuthUserBO authUserBO);

    List<AuthUserBO> listUserInfoByIds(List<String> userNameList);

    SaTokenInfo doLogin(String validCode);
}
