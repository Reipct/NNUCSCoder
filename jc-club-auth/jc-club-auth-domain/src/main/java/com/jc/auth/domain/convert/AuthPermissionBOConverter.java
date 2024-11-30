package com.jc.auth.domain.convert;


import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description Permission entity转换器
 * @Author 12919
 * @Date 2024/11/21
 */
@Mapper
public interface AuthPermissionBOConverter {
    AuthPermissionBOConverter INSTANCE= Mappers.getMapper(AuthPermissionBOConverter.class);

    /**
     * Permission:BO->Entity
     * @param authPermissionBO
     * @return
     */
    AuthPermission convertBOToEntityPermission(AuthPermissionBO authPermissionBO);

}
