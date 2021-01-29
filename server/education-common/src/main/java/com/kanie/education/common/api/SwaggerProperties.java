package com.kanie.education.common.api;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：kanie
 * @date ：Created in 2020/10/30 22:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SwaggerProperties {

    /**
     * 生成API文档的包
     */
    private String apiBasePackage;

    /**
     * 是否开启登录认证
     */
    private boolean isSecurity;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String desc;

    /**
     * 版本
     */
    private String version;

    /**
     * 联系人名
     */
    private String contactName;

    /**
     * 联系人网址
     */
    private String contactUrl;

    /**
     * 联系人邮箱
     */
    private String contactEmail;
}
