package com.jc.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jc.auth.application.convert.AuthUserDTOConverter;
import com.jc.auth.application.dto.AuthUserDTO;
import com.jc.auth.common.entity.Result;
import com.jc.auth.domain.entity.AuthUserBO;
import com.jc.auth.domain.service.impl.AuthUserDomainServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author 12919
 * @Date 2024/11/21
 */
@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    @Resource
    private AuthUserDomainServiceImpl authUserDomainService;

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register.add.dto.{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "邮件地址不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "密码不能为空");

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBOAuth(authUserDTO);
            authUserDomainService.register(authUserBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("UserController.register.add.error.{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


    // 测试登录，浏览器访问： http://localhost:3011/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(validCode), "验证码不能为空");
            return Result.ok(authUserDomainService.doLogin(validCode));
        }catch (Exception e){
            log.error("UserController.doLogin.error.{}", e.getMessage(), e);
            return Result.fail("用户登录失败");
        }


    }

    // 查询登录状态，浏览器访问： http://localhost:3011/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {

        return "当前会话是否登录：" + StpUtil.isLogin();
    }


}
