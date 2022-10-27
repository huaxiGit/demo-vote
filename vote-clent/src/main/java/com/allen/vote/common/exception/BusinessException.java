package com.allen.vote.common.exception;

import lombok.Data;

/**
 * 业务异常
 */
@Data
public class BusinessException  extends RuntimeException{


    private ExceptionEnum exceptionEnum;
    private Integer code;
    private String errorMsg;

    public BusinessException() {
        super();
    }

    public BusinessException(ExceptionEnum exceptionEnum) {
        super("{code:" + exceptionEnum.getCode() + ",errorMsg:" + exceptionEnum.getMsg() + "}");
        this.exceptionEnum = exceptionEnum;
        this.code = exceptionEnum.getCode();
        this.errorMsg = exceptionEnum.getMsg();
    }

    public BusinessException(Integer code, String errorMsg) {
        super("{code:" + code + ",errorMsg:" + errorMsg + "}");
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer code, String errorMsg, Object... args) {
        super("{code:" + code + ",errorMsg:" + String.format(errorMsg, args) + "}");
        this.code = code;
        this.errorMsg = String.format(errorMsg, args);
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }




}
