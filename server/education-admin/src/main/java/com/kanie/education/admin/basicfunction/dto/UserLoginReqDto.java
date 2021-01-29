package com.kanie.education.admin.basicfunction.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录请求dto
 * @author ：kanie
 * @date ：Created in 2021/1/13 17:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginReqDto {

    @NotEmpty
    @ApiModelProperty(value = "登录账号", required = true)
    private String loginAccount;

    @NotEmpty
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
