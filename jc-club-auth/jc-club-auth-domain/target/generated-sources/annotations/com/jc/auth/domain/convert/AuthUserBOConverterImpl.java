package com.jc.auth.domain.convert;

import com.jc.auth.domain.entity.AuthUserBO;
import com.jc.auth.infra.basic.entity.AuthUser;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-22T16:39:49+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class AuthUserBOConverterImpl implements AuthUserBOConverter {

    @Override
    public AuthUser convertBOToEntityAuth(AuthUserBO authUserBO) {
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
