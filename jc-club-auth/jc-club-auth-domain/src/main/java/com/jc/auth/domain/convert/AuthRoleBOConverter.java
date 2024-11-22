package com.jc.auth.domain.convert;


import com.jc.auth.domain.entity.AuthRoleBO;
import com.jc.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 角色BO转换器
 * @Author 12919
 * @Date 2024/11/21
 */
@Mapper
public interface AuthRoleBOConverter {
    AuthRoleBOConverter INSTANCE= Mappers.getMapper(AuthRoleBOConverter.class);

    /**
     * Auth:BO->Entity
     * @param authRoleBO
     * @return
     */
    AuthRole convertBOToEntityRole(AuthRoleBO authRoleBO);

}
