package com.jc.auth.application.convert;

import com.jc.auth.application.dto.AuthPermissionDTO;
import com.jc.auth.application.dto.AuthRolePermissionDTO;
import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description ROLE DTO转换器
 * @Author 12919
 * @Date 2024/11/21
 */
@Mapper
public interface AuthRolePermissionDTOConverter {
    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    /**
     * Auth role Permission:DTO->BO
     *
     * @param authRolePermissionDTO
     * @return
     */
    AuthRolePermissionBO convertDTOToBORolePermission(AuthRolePermissionDTO authRolePermissionDTO);

}
