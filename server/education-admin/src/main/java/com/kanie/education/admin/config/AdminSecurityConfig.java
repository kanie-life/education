package com.kanie.education.admin.config;

import cn.hutool.core.collection.CollUtil;
import com.kanie.education.admin.basicfunction.po.UmResource;
import com.kanie.education.admin.basicfunction.service.IUmUserService;
import com.kanie.education.security.component.DynamicSecurityService;
import com.kanie.education.security.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * security模块相关配置
 * @author ：kanie
 * @date ：Created in 2020/11/10 11:12
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminSecurityConfig extends WebSecurityConfig {

    @Autowired
    private IUmUserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> userService.queryUserDetailsByLoginAccount(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<UmResource> umResources = userService.queryResourceListAll();
                if (CollUtil.isNotEmpty(umResources)) {
                    for (UmResource umResource : umResources) {
                        if (umResource.getUrl() != null) {
                            map.put(umResource.getUrl(), new SecurityConfig(umResource.getResourceId() + ":" + umResource.getName()));
                        }
                    }
                }
                return map;
            }
        };
    }
}
