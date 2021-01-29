package com.kanie.education.common.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志封装类
 * @author ：kanie
 * @date ：Created in 2020/10/31 16:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebLogProperties {

    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String userName;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求方法
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 返回结果
     */
    private Object result;
}
