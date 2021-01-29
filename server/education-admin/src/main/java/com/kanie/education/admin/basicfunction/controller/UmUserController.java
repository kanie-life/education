package com.kanie.education.admin.basicfunction.controller;

import com.kanie.education.admin.basicfunction.dto.UserLoginReqDto;
import com.kanie.education.admin.basicfunction.service.IUmUserService;
import com.kanie.education.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理控制器
 * @author ：kanie
 * @date ：Created in 2021/1/13 16:35
 */
@Controller
@Api(tags = "用户管理", description = "用户管理")
@RequestMapping("/userManage")
public class UmUserController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private IUmUserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login.do")
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UserLoginReqDto userLoginReqDto) {
        String token = null;
        try {
            token = userService.login(userLoginReqDto);
        } catch (AuthenticationException e) {
            return CommonResult.failed("用户名或密码错误");
        }
        if (token == null) {
            return CommonResult.failed("用户名或密码错误");
        }
        Map<String, String> resMap = new HashMap<>();
        resMap.put("token", token);
        resMap.put("tokenHead", tokenHead);
        return CommonResult.success("用户登录成功", resMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/info.do")
    @ResponseBody
    public CommonResult queryUserInfo(Principal principal) {
        if (principal != null) {
            Map<String, Object> resMap = userService.queryUserInfo(principal.getName());
            if (resMap != null) {
                return CommonResult.success("获取用户信息成功", resMap);
            }
        }
        return CommonResult.unauthorized(null);
    }

    @ApiOperation(value = "用户登出")
    @PostMapping(value = "/logout.do")
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success("登出成功", null);
    }
}
