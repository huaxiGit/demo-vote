package com.allen.vote.common.exception;

import com.allen.vote.common.response.BusinessResult;
import com.allen.vote.common.util.ErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public BusinessResult exceptionHandler(BusinessException e) {
        log.error(ErrorUtil.errorInfoToString(e));
        return BusinessResult.error(e.getCode(), e.getErrorMsg());
    }

    /**
     * 未知异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BusinessResult exceptionHandler(Exception e) {
        // 把错误信息输入到日志中
        log.error(ErrorUtil.errorInfoToString(e));
        return BusinessResult.error(ExceptionEnum.UNKNOWN.getCode(),
                ExceptionEnum.UNKNOWN.getMsg());
    }


    /**
     * 参数校验异常
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BusinessResult argumentExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        log.error(ErrorUtil.errorInfoToString(e));
        log.info("发生参数异常:{}", message);
        return BusinessResult.error(ExceptionEnum.PARAM_ERROR.getCode(), message);
    }


    /**
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public BusinessResult exceptionHandler(NullPointerException e) {
        log.error(ErrorUtil.errorInfoToString(e));
        return BusinessResult.error(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode(),
                ExceptionEnum.INTERNAL_SERVER_ERROR.getMsg());
    }
}
