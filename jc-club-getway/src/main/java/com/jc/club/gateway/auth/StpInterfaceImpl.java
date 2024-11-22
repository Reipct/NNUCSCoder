package com.jc.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description 自定义权限验证接口扩展
 * @Author 12919
 * @Date 2024/11/22
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        //redis缓存,没有的话调微服务去获取
        List<String> permissionList =new LinkedList<>();
        permissionList.add("subject:add");
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        List<String> adminList =new LinkedList<>();
        adminList.add("admin");
        return adminList;
    }

}

