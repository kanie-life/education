package com.kanie.education.admin.basicfunction.service;

import com.kanie.education.admin.basicfunction.dto.UserLoginReqDto;
import com.kanie.education.admin.basicfunction.po.UmResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

public interface IUmUserService {

    /**
     * 获取全部资源
     * @return
     */
    List<UmResource> queryResourceListAll();

    /**
     * 根据登录账号查询用户信息
     * @param loginAccount
     * @return
     */
    UserDetails queryUserDetailsByLoginAccount(String loginAccount);

    /**
     * 用户登录
     * @param userLoginReqDto
     */
    String login(UserLoginReqDto userLoginReqDto);

    /**
     * 根据登录账号查询用户信息
     * @param loginAccount
     * @return
     */
    Map<String, Object> queryUserInfo(String loginAccount);
}
