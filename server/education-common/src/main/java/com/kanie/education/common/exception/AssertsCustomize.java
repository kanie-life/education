package com.kanie.education.common.exception;

import com.kanie.education.common.api.IErrorCode;

/**
 * 自定义断言类
 * @author ：kanie
 * @date ：Created in 2021/1/13 17:57
 */
public class AssertsCustomize {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
