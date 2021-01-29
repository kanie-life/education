package com.kanie.education.common.api;

/**
 * @author ：kanie
 * @date ：Created in 2020/11/5 22:37
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "成功"),
    FAIL(0, "失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
