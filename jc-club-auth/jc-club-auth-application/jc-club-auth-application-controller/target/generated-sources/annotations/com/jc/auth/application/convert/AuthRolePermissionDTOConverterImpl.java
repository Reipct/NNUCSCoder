package com.jc.auth.application.convert;

import com.jc.auth.application.dto.AuthRolePermissionDTO;
import com.jc.auth.domain.entity.AuthRolePermissionBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-28T18:45:54+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class AuthRolePermissionDTOConverterImpl implements AuthRolePermissionDTOConverter {

    @Override
    public AuthRolePermissionBO convertDTOToBORolePermission(AuthRolePermissionDTO authRolePermissionDTO) {
        if ( authRolePermissionDTO == null ) {
            return null;
        }

        AuthRolePermissionBO authRolePermissionBO = new AuthRolePermissionBO();

        authRolePermissionBO.setId( authRolePermissionDTO.getId() );
        authRolePermissionBO.setRoleId( authRolePermissionDTO.getRoleId() );
        authRolePermissionBO.setPermissionId( authRolePermissionDTO.getPermissionId() );
        List<Long> list = authRolePermissionDTO.getPermissionIdList();
        if ( list != null ) {
            authRolePermissionBO.setPermissionIdList( new ArrayList<Long>( list ) );
        }

        return authRolePermissionBO;
    }
}
