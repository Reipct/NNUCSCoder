package com.jc.auth.domain.convert;

import com.jc.auth.domain.entity.AuthRolePermissionBO;
import com.jc.auth.infra.basic.entity.AuthRolePermission;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-28T18:44:25+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class AuthRolePermissionBOConverterImpl implements AuthRolePermissionBOConverter {

    @Override
    public AuthRolePermission convertBOToEntityRolePermission(AuthRolePermissionBO authRolePermissionBO) {
        if ( authRolePermissionBO == null ) {
            return null;
        }

        AuthRolePermission authRolePermission = new AuthRolePermission();

        authRolePermission.setId( authRolePermissionBO.getId() );
        authRolePermission.setRoleId( authRolePermissionBO.getRoleId() );
        authRolePermission.setPermissionId( authRolePermissionBO.getPermissionId() );

        return authRolePermission;
    }
}
