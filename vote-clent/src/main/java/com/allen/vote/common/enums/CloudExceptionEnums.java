package com.allen.vote.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定义特定异常
 */
@Getter
@AllArgsConstructor
public enum CloudExceptionEnums {
    AUTH_VERIFY_SMS_ERROR(20001, "短信验证码输入错误"),
    AUTH_SMS_NONE_ERROR(20002, "请先获取短信验证码"),
    AUTH_VERIFY_PHONE_ERROR(20003, "手机号码格式错误"),
    ;

    private Integer errorCode;
    private String message;
}
