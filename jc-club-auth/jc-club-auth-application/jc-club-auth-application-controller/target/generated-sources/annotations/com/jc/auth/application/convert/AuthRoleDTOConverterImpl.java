package com.jc.auth.application.convert;

import com.jc.auth.application.dto.AuthRoleDTO;
import com.jc.auth.domain.entity.AuthRoleBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-28T15:48:19+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class AuthRoleDTOConverterImpl implements AuthRoleDTOConverter {

    @Override
    public AuthRoleBO convertDTOToBORole(AuthRoleDTO authRoleDTO) {
        if ( authRoleDTO == null ) {
            return null;
        }

        AuthRoleBO authRoleBO = new AuthRoleBO();

        authRoleBO.setId( authRoleDTO.getId() );
        authRoleBO.setRoleName( authRoleDTO.getRoleName() );
        authRoleBO.setRoleKey( authRoleDTO.getRoleKey() );
        authRoleBO.setCreatedBy( authRoleDTO.getCreatedBy() );
        authRoleBO.setCreatedTime( authRoleDTO.getCreatedTime() );
        authRoleBO.setUpdateBy( authRoleDTO.getUpdateBy() );
        authRoleBO.setUpdateTime( authRoleDTO.getUpdateTime() );
        authRoleBO.setIsDeleted( authRoleDTO.getIsDeleted() );

        return authRoleBO;
    }
}
