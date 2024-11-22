package com.jc.auth.domain.convert;

import com.jc.auth.domain.entity.AuthRoleBO;
import com.jc.auth.infra.basic.entity.AuthRole;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-22T20:32:39+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class AuthRoleBOConverterImpl implements AuthRoleBOConverter {

    @Override
    public AuthRole convertBOToEntityRole(AuthRoleBO authRoleBO) {
        if ( authRoleBO == null ) {
            return null;
        }

        AuthRole authRole = new AuthRole();

        authRole.setId( authRoleBO.getId() );
        authRole.setRoleName( authRoleBO.getRoleName() );
        authRole.setRoleKey( authRoleBO.getRoleKey() );
        authRole.setCreatedBy( authRoleBO.getCreatedBy() );
        authRole.setCreatedTime( authRoleBO.getCreatedTime() );
        authRole.setUpdateBy( authRoleBO.getUpdateBy() );
        authRole.setUpdateTime( authRoleBO.getUpdateTime() );
        authRole.setIsDeleted( authRoleBO.getIsDeleted() );

        return authRole;
    }
}
