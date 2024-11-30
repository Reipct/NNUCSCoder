package com.jc.auth.domain.convert;


import com.jc.auth.domain.entity.AuthRolePermissionBO;
import com.jc.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description Permission entity转换器
 * @Author 12919
 * @Date 2024/11/21
 */
@Mapper
public interface AuthRolePermissionBOConverter {
    AuthRolePermissionBOConverter INSTANCE= Mappers.getMapper(AuthRolePermissionBOConverter.class);

    /**
     * Permission:BO->Entity
     * @param authRolePermissionBO
     * @return
     */
    AuthRolePermission convertBOToEntityRolePermission(AuthRolePermissionBO authRolePermissionBO);

}
