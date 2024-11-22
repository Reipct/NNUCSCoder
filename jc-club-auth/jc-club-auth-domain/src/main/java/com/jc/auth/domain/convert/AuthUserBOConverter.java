package com.jc.auth.domain.convert;


import com.jc.auth.domain.entity.AuthUserBO;
import com.jc.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 用户BO转换器
 * @Author 12919
 * @Date 2024/11/21
 */
@Mapper
public interface AuthUserBOConverter {
    AuthUserBOConverter INSTANCE= Mappers.getMapper(AuthUserBOConverter.class);

    /**
     * Auth:BO->Entity
     * @param authUserBO
     * @return
     */
    AuthUser convertBOToEntityAuth(AuthUserBO authUserBO);

}
