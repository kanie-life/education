package com.kanie.education.common.exception;

import com.kanie.education.common.api.IErrorCode;

/**
 * 自定义API异常
 * @author ：kanie
 * @date ：Created in 2021/1/13 17:51
 */
public class ApiException extends RuntimeException {

    private IErrorCode errorCode;

    public IErrorCode getErrorCode() {
        return errorCode;
    }

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    /**
     * 默认的构造方法。
     */
    public ApiException() {
    }

    /**
     * 自定义的构造方法1。
     *
     * @param cause Throwable
     */
    public ApiException(Throwable cause) {
        super(cause);
    }

    /**
     * 自定义的构造方法2。
     *
     * @param message String
     */
    public ApiException(String message){
        super(message);
    }

    /**
     * 自定义的构造方法3。
     *
     * @param message String
     * @param cause Throwable
     */
    public ApiException(String message, Throwable cause){
        super(message, cause);
    }
}
