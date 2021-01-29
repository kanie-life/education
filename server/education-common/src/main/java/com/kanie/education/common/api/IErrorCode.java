package com.kanie.education.common.api;

/**
 * 封装返回码
 * @author ：kanie
 * @date ：Created in 2020/11/5 22:35
 */
public interface IErrorCode {
    /**
     * 返回码
     * @return
     */
    long getCode();

    /**
     * 说明信息
     * @return
     */
    String getMessage();
}
