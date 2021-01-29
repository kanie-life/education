package com.kanie.education.admin.config;

import com.kanie.education.common.api.SwaggerProperties;
import com.kanie.education.common.config.BaseSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author ：kanie
 * @date ：Created in 2020/10/27 15:39
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.kanie.education.admin.basicfunction.controller")
                .title("education后台系统")
                .desc("education后台相关接口")
                .contactName("kanie")
                .contactEmail("1668464911@qq.com")
                .version("1.0")
                .isSecurity(true)
                .build();
    }
}
