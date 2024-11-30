package com.jc.auth.application.convert;

import com.jc.auth.application.dto.AuthPermissionDTO;
import com.jc.auth.application.dto.AuthRoleDTO;
import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description ROLE DTO转换器
 * @Author 12919
 * @Date 2024/11/21
 */
@Mapper
public interface AuthPermissionDTOConverter {
    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);

    /**
     * Auth role:DTO->BO
     *
     * @param authPermissionDTO
     * @return
     */
    AuthPermissionBO convertDTOToBOPermission(AuthPermissionDTO authPermissionDTO);

}
