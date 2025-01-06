package com.jc.auth.domain.convert;


import com.jc.auth.domain.entity.AuthUserBO;
import com.jc.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description 用户BO转换器
 * @Author 12919
 * @Date 2024/11/21
 */
@Mapper
public interface AuthUserBOConverter {
    AuthUserBOConverter INSTANCE= Mappers.getMapper(AuthUserBOConverter.class);



    AuthUserBO convertEntityToBO(AuthUser authUser);

    List<AuthUserBO> convertEntityToBO(List<AuthUser> authUserList);

    AuthUser convertBOToEntity(AuthUserBO authUserBO);
}
