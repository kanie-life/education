package com.kanie.education.common.api;

/**
 * 通用返回对象
 */
public class CommonResult {

    /**
     * 状态码, 0：成功；1：失败
     */
    private Long code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据封装
     */
    private Object data;

    protected CommonResult() {
    }

    protected CommonResult(Long code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     * @param message
     * @param data 获取的数据
     */
    public static CommonResult success(String message, Object data) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param message
     */
    public static CommonResult failed(String message) {
        return new CommonResult(ResultCode.FAIL.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param errorCode
     * @return
     */
    public static CommonResult failed(IErrorCode errorCode) {
        return new CommonResult(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 参数验证失败返回结果
     * @param message
     * @return
     */
    public static CommonResult validateFailed(String message) {
        return new CommonResult(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static CommonResult unauthorized(Object data) {
        return new CommonResult(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static CommonResult forbidden(Object data) {
        return new CommonResult(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
