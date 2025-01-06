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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            authUserDomainService.register(authUserBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("UserController.register.add.error.{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error:{}", e.getMessage(), e);
            return Result.fail("更新用户信息失败");
        }
    }
//
    /**
     * 获取用户信息
     */
    @RequestMapping("getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.getUserInfo.dto:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            if(!userInfo.isSuccess())
                return Result.fail("暂时无法获取用户信息，稍后再试");
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfo));
        } catch (Exception e) {
            log.error("UserController.update.error:{}", e.getMessage(), e);
            return Result.fail("获取用户信息失败");
        }
    }

    /**
     * 批量获取用户信息
     */
    @RequestMapping("listByIds")
    public Result<List<AuthUserDTO>> listUserInfoByIds(@RequestBody List<String> userNameList) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.listUserInfoByIds.dto:{}", JSON.toJSONString(userNameList));
            }
            Preconditions.checkArgument(!CollectionUtils.isEmpty(userNameList), "id集合不能为空");
            List<AuthUserBO> userInfos = authUserDomainService.listUserInfoByIds(userNameList);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOToDTO(userInfos));
        } catch (Exception e) {
            log.error("UserController.listUserInfoByIds.error:{}", e.getMessage(), e);
            return Result.fail("批量获取用户信息失败");
        }
    }

    /**
     * 用户退出
     */
    @RequestMapping("logOut")
    public Result logOut(@RequestParam String userName) {
        try {
            log.info("UserController.logOut.userName:{}", userName);
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户名不能为空");
            StpUtil.logout(userName);
            return Result.ok();
        } catch (Exception e) {
            log.error("UserController.logOut.error:{}", e.getMessage(), e);
            return Result.fail("用户登出失败");
        }
    }

    /**
     * 删除用户
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOToBO(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.error("UserController.delete.error:{}", e.getMessage(), e);
            return Result.fail("删除用户信息失败");
        }
    }
    //
    private void checkUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
    }
//



    /**
     * 登录
     */
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
