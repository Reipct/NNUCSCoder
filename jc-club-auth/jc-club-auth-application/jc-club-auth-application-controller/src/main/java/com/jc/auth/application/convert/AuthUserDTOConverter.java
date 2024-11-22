package com.jc.auth.application.convert;

import com.jc.auth.application.dto.AuthUserDTO;
import com.jc.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 用户DTO转换器
 * @Author 12919
 * @Date 2024/11/21
 */
@Mapper
public interface AuthUserDTOConverter {
    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);

    /**
     * Auth:DTO->BO
     *
     * @param authUserDTO
     * @return
     */
    AuthUserBO convertDTOToBOAuth(AuthUserDTO authUserDTO);

}
