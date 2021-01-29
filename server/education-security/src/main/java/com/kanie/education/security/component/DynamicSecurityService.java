package com.kanie.education.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * 动态权限相关业务接口
 * @author ：kanie
 * @date ：Created in 2020/11/9 10:29
 */
public interface DynamicSecurityService {

    /**
     * 加载资源通配符和资源对应map
     * @return
     */
    Map<String, ConfigAttribute> loadDataSource();
}
