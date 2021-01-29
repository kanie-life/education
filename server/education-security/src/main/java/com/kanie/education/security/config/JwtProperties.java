package com.kanie.education.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：kanie
 * @date ：Created in 2020/11/6 15:28
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * JWT存储的请求头
     */
    private String tokenHeader;

    /**
     * JWT加解密使用的密钥
     */
    private String secret;

    /**
     * JWT的超期限时间
     */
    private Long expiration;

    /**
     * JWT负载中拿到开头
     */
    private String tokenHead;
}
