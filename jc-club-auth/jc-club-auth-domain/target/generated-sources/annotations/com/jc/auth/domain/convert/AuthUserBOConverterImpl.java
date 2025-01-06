package com.jc.auth.domain.convert;

import com.jc.auth.domain.entity.AuthUserBO;
import com.jc.auth.infra.basic.entity.AuthUser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-31T17:02:18+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class AuthUserBOConverterImpl implements AuthUserBOConverter {

    @Override
    public AuthUserBO convertEntityToBO(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }

        AuthUserBO authUserBO = new AuthUserBO();

        authUserBO.setId( authUser.getId() );
        authUserBO.setUserName( authUser.getUserName() );
        authUserBO.setNickName( authUser.getNickName() );
        authUserBO.setEmail( authUser.getEmail() );
        authUserBO.setPhone( authUser.getPhone() );
        authUserBO.setPassword( authUser.getPassword() );
        authUserBO.setSex( authUser.getSex() );
        authUserBO.setAvatar( authUser.getAvatar() );
        authUserBO.setStatus( authUser.getStatus() );
        authUserBO.setIntroduce( authUser.getIntroduce() );
        authUserBO.setExtJson( authUser.getExtJson() );
        authUserBO.setCreatedBy( authUser.getCreatedBy() );
        authUserBO.setCreatedTime( authUser.getCreatedTime() );
        authUserBO.setUpdateBy( authUser.getUpdateBy() );
        authUserBO.setUpdateTime( authUser.getUpdateTime() );
        authUserBO.setIsDeleted( authUser.getIsDeleted() );

        return authUserBO;
    }

    @Override
    public List<AuthUserBO> convertEntityToBO(List<AuthUser> authUserList) {
        if ( authUserList == null ) {
            return null;
        }

        List<AuthUserBO> list = new ArrayList<AuthUserBO>( authUserList.size() );
        for ( AuthUser authUser : authUserList ) {
            list.add( convertEntityToBO( authUser ) );
        }

        return list;
    }

    @Override
    public AuthUser convertBOToEntity(AuthUserBO authUserBO) {
        if ( authUserBO == null ) {
            return null;
        }

        AuthUser authUser = new AuthUser();

        authUser.setId( authUserBO.getId() );
        authUser.setUserName( authUserBO.getUserName() );
        authUser.setNickName( authUserBO.getNickName() );
        authUser.setEmail( authUserBO.getEmail() );
        authUser.setPhone( authUserBO.getPhone() );
        authUser.setPassword( authUserBO.getPassword() );
        authUser.setSex( authUserBO.getSex() );
        authUser.setAvatar( authUserBO.getAvatar() );
        authUser.setStatus( authUserBO.getStatus() );
        authUser.setIntroduce( authUserBO.getIntroduce() );
        authUser.setExtJson( authUserBO.getExtJson() );
        authUser.setCreatedBy( authUserBO.getCreatedBy() );
        authUser.setCreatedTime( authUserBO.getCreatedTime() );
        authUser.setUpdateBy( authUserBO.getUpdateBy() );
        authUser.setUpdateTime( authUserBO.getUpdateTime() );
        authUser.setIsDeleted( authUserBO.getIsDeleted() );

        return authUser;
    }
}
