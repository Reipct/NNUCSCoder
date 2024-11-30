package com.jc.auth.application.convert;

import com.jc.auth.application.dto.AuthPermissionDTO;
import com.jc.auth.domain.entity.AuthPermissionBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-28T16:20:21+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class AuthPermissionDTOConverterImpl implements AuthPermissionDTOConverter {

    @Override
    public AuthPermissionBO convertDTOToBOPermission(AuthPermissionDTO authPermissionDTO) {
        if ( authPermissionDTO == null ) {
            return null;
        }

        AuthPermissionBO authPermissionBO = new AuthPermissionBO();

        authPermissionBO.setId( authPermissionDTO.getId() );
        authPermissionBO.setName( authPermissionDTO.getName() );
        authPermissionBO.setParentId( authPermissionDTO.getParentId() );
        authPermissionBO.setType( authPermissionDTO.getType() );
        authPermissionBO.setMenuUrl( authPermissionDTO.getMenuUrl() );
        authPermissionBO.setStatus( authPermissionDTO.getStatus() );
        authPermissionBO.setShow( authPermissionDTO.getShow() );
        authPermissionBO.setIcon( authPermissionDTO.getIcon() );
        authPermissionBO.setPermissionKey( authPermissionDTO.getPermissionKey() );
        authPermissionBO.setCreatedBy( authPermissionDTO.getCreatedBy() );
        authPermissionBO.setCreatedTime( authPermissionDTO.getCreatedTime() );
        authPermissionBO.setUpdateBy( authPermissionDTO.getUpdateBy() );
        authPermissionBO.setUpdateTime( authPermissionDTO.getUpdateTime() );
        authPermissionBO.setIsDeleted( authPermissionDTO.getIsDeleted() );

        return authPermissionBO;
    }
}
