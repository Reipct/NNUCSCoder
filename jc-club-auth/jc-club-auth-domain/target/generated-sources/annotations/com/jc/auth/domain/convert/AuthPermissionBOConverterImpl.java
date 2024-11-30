package com.jc.auth.domain.convert;

import com.jc.auth.domain.entity.AuthPermissionBO;
import com.jc.auth.infra.basic.entity.AuthPermission;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-28T16:20:20+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class AuthPermissionBOConverterImpl implements AuthPermissionBOConverter {

    @Override
    public AuthPermission convertBOToEntityPermission(AuthPermissionBO authPermissionBO) {
        if ( authPermissionBO == null ) {
            return null;
        }

        AuthPermission authPermission = new AuthPermission();

        authPermission.setId( authPermissionBO.getId() );
        authPermission.setName( authPermissionBO.getName() );
        authPermission.setParentId( authPermissionBO.getParentId() );
        authPermission.setType( authPermissionBO.getType() );
        authPermission.setMenuUrl( authPermissionBO.getMenuUrl() );
        authPermission.setStatus( authPermissionBO.getStatus() );
        authPermission.setShow( authPermissionBO.getShow() );
        authPermission.setIcon( authPermissionBO.getIcon() );
        authPermission.setPermissionKey( authPermissionBO.getPermissionKey() );
        authPermission.setCreatedBy( authPermissionBO.getCreatedBy() );
        authPermission.setCreatedTime( authPermissionBO.getCreatedTime() );
        authPermission.setUpdateBy( authPermissionBO.getUpdateBy() );
        authPermission.setUpdateTime( authPermissionBO.getUpdateTime() );
        authPermission.setIsDeleted( authPermissionBO.getIsDeleted() );

        return authPermission;
    }
}
