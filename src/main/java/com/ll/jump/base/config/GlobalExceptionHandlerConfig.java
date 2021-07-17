package com.ll.jump.base.config;

import com.ll.jump.base.exception.ServiceErrorCode;
import com.ll.jump.base.exception.ServiceException;
import com.ll.jump.base.result.ServiceResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandlerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerConfig.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServiceResult errorHandler(Exception e) {
        LOGGER.error("error:{}", e.getMessage(), e);
        String code = ServiceErrorCode.E008999.getCode();
        String message = ServiceErrorCode.E008999.getDesc();
        if (e instanceof BindException) {// on form submit
            BindException bindException = (BindException)e;
            List<FieldError> errorList = bindException.getFieldErrors();
            FieldError f_error = errorList.get(0);
            code = ServiceErrorCode.E008998.getCode();
            message = ServiceErrorCode.E008999.getDesc() + "," + f_error.getDefaultMessage();

        } else if (e instanceof MethodArgumentNotValidException) {// on json submit
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException)e;
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            FieldError f_error = bindingResult.getFieldError();
            code = ServiceErrorCode.E008998.getCode();
            message = ServiceErrorCode.E008999.getDesc() + "," + f_error.getDefaultMessage();
        } else if (e instanceof ServiceException) {
            ServiceException se = (ServiceException)e;
            code = se.getCode();
            message = se.getMessage();
        }
        return ServiceResult.fail(code, message);
    }

}
