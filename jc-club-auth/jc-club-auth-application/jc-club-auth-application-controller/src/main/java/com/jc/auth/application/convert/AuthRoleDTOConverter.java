package com.jc.auth.application.convert;

import com.jc.auth.application.dto.AuthRoleDTO;
import com.jc.auth.application.dto.AuthUserDTO;
import com.jc.auth.domain.entity.AuthRoleBO;
import com.jc.auth.domain.entity.AuthUserBO;
import com.jc.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description ROLE DTO转换器
 * @Author 12919
 * @Date 2024/11/21
 */
@Mapper
public interface AuthRoleDTOConverter {
    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);

    /**
     * Auth role:DTO->BO
     *
     * @param authRoleDTO
     * @return
     */
    AuthRoleBO convertDTOToBORole(AuthRoleDTO authRoleDTO);

}
