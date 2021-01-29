package com.kanie.education.admin.basicfunction.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.kanie.education.admin.basicfunction.constant.PrefixSQLConstant;
import com.kanie.education.admin.basicfunction.dto.UserLoginReqDto;
import com.kanie.education.admin.basicfunction.po.UmResource;
import com.kanie.education.admin.basicfunction.po.UmRole;
import com.kanie.education.admin.basicfunction.po.UmUser;
import com.kanie.education.admin.basicfunction.security.AdminUserDetails;
import com.kanie.education.admin.basicfunction.service.IUmUserService;
import com.kanie.education.common.exception.AssertsCustomize;
import com.kanie.education.common.dao.IBaseDao;
import com.kanie.education.security.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：kanie
 * @date ：Created in 2020/10/28 22:59
 */
@Service
public class UmUserServiceImpl implements IUmUserService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UmUserServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IBaseDao baseDao;

    @Override
    public List<UmResource> queryResourceListAll() {
        return baseDao.selectBy(PrefixSQLConstant.SQL_PREFIX_ID_UM_ROLE + "queryResourceList", new HashMap<>());
    }

    @Override
    public String login(UserLoginReqDto userLoginReqDto) {
        UserDetails userDetails = queryUserDetailsByLoginAccount(userLoginReqDto.getLoginAccount());
        if(!passwordEncoder.matches(userLoginReqDto.getPassword(), userDetails.getPassword())){
            AssertsCustomize.fail("密码不正确");
        }
        if(!userDetails.isEnabled()){
            AssertsCustomize.fail("帐号已被禁用");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public UserDetails queryUserDetailsByLoginAccount(String loginAccount) {
        UmUser umUser = this.queryUserByLoginAccount(loginAccount);
        if (umUser != null) {
            List<UmResource> resourceList = queryMenuListByUserId(umUser.getUserId());
            LOGGER.info("{}用户的菜单列表：{}", umUser.getUserName(), resourceList.toString());
            return new AdminUserDetails(umUser, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    public List<UmResource> queryMenuListByUserId(Long userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        return baseDao.selectBy(PrefixSQLConstant.SQL_PREFIX_ID_UM_ROLE + "queryResourceListByUserId", param);
    }

    public UmUser queryUserByLoginAccount(String loginAccount) {
        return baseDao.selectSingleBy(PrefixSQLConstant.SQL_PREFIX_ID_UM_USER + "queryUserBy", "loginAccount", loginAccount);
    }

    @Override
    public Map<String, Object> queryUserInfo(String loginAccount) {
        Map<String, Object> data = new HashMap<>();
        UmUser umUser = this.queryUserByLoginAccount(loginAccount);
        if (umUser != null) {
            data.put("username", umUser.getUserName());
            data.put("menus", baseDao.selectBy(PrefixSQLConstant.SQL_PREFIX_ID_UM_ROLE + "queryMenuListByUserId", "userId", umUser.getUserId()));
            data.put("icon", umUser.getHeadImageUrl());
            List<UmRole> roleList = baseDao.selectBy(PrefixSQLConstant.SQL_PREFIX_ID_UM_ROLE + "queryRoleByUserId", "userId", umUser.getUserId());
            if(CollUtil.isNotEmpty(roleList)){
                List<String> roles = roleList.stream().map(UmRole::getRoleName).collect(Collectors.toList());
                data.put("roles",roles);
            }
        }
        return data;
    }
}
