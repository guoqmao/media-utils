package com.harzone.media.controller.exception;

import com.harzone.media.controller.result.JsonResult;
import com.harzone.media.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常拦截
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public JsonResult userException(Exception e){
        JsonResult resultData;

        // 请求方法错误
        if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException = (HttpRequestMethodNotSupportedException) e;
            resultData = JsonResult.fail(httpRequestMethodNotSupportedException.getMessage());
        }
        // 参数错误
        else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            FieldError fieldError = bindingResult.getFieldErrors().get(0);
            resultData = JsonResult.fail(fieldError.getDefaultMessage());
        }
        // 缺少参数，spring抛出的异常
        else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException missingServletRequestParameterException = (MissingServletRequestParameterException) e;
            resultData = JsonResult.fail(missingServletRequestParameterException.getMessage());
        }
        // 错误URL
        else if (e instanceof NoHandlerFoundException) {
            resultData = JsonResult.fail(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        // 错误请求头
        else if (e instanceof HttpMediaTypeNotSupportedException) {
            HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException = (HttpMediaTypeNotSupportedException) e;
            resultData = JsonResult.fail(httpMediaTypeNotSupportedException.getMessage());
        }
        // 错误参数类型
        else if (e instanceof HttpMessageNotReadableException) {
            resultData = JsonResult.fail("Required request body is missing");
        }
        // 主动抛的业务异常
        else if (e instanceof ServiceException){
            ServiceException serviceException = (ServiceException) e;
            resultData = JsonResult.fail(serviceException.getMessage());
        }
        // 未捕获的异常，直接抛
        else {
            resultData = JsonResult.fail("服务器处理失败！");
        }
        log.error("[GlobalExceptionHandler] :", e);
        return resultData;
    }
}