package com.harzone.media.controller.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harzone.media.enums.HttpCodeEnum;
import org.springframework.util.StringUtils;

/**
 * 用于封装异步调用返回结果
 *
 * @param <T>
 */
public class JsonResult<T> {

    private static final String COMMON_ERROR_MESSAGE = "系统出现了一点问题，请稍后再试";
    public static final String SUCCESS_MSG = "success";
    public static final String FAIL_MSG = "fail";
    public static final int SUCCESS_CODE = HttpCodeEnum.SUCCESS.getCode();
    public static final int FAIL_CODE = HttpCodeEnum.SYSTEM_ERR.getCode();

    /**
     * 返回状态码
     */
    //@ApiModelProperty(value = "返回状态码；200:成功")
    private int code;

    /**
     * 调用结果
     */
    //@ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 结果消息，如果调用成功，消息通常为空
     */
    //@ApiModelProperty(value = "结果消息")
    private String msg;

    private String success;

    protected JsonResult() {
    }

    /**
     * @param code 代码
     * @param data 结果
     * @param msg  消息
     */
    public JsonResult(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * @param code 代码
     * @param msg  消息
     */
    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 请求成功消息
     *
     * @param data
     * @return
     */
    public static <E> JsonResult<E> success(E data) {
        return new JsonResult<E>(SUCCESS_CODE, data, SUCCESS_MSG);
    }

    /**
     * 请求成功消息
     *
     * @param data
     * @return
     */
    public static <E> JsonResult<E> success(int code, E data) {
        return new JsonResult<E>(code, data, SUCCESS_MSG);
    }

    /**
     * 请求成功消息
     *
     * @return
     */
    public static <E> JsonResult<E> success() {
        return new JsonResult<E>(SUCCESS_CODE, SUCCESS_MSG);
    }


    /**
     * 请求成功方法 ，data返回值，msg提示信息
     *
     * @param data
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> success(E data, String msg) {
        return new JsonResult<E>(SUCCESS_CODE, data, msg);
    }
    
    /**
     * 请求失败消息
     * @return
     */
    public static <E> JsonResult<E> fail() {
        return new JsonResult<>(FAIL_CODE, COMMON_ERROR_MESSAGE);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> fail(String msg) {
        return new JsonResult<>(FAIL_CODE, StringUtils.isEmpty(msg) ? COMMON_ERROR_MESSAGE : msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> fail(Integer code, String msg) {
        return new JsonResult<>(code, StringUtils.isEmpty(msg) ? COMMON_ERROR_MESSAGE : msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> fail(Integer code, E data, String msg) {
        return new JsonResult<>(code, data, StringUtils.isEmpty(msg) ? COMMON_ERROR_MESSAGE : msg);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> unknownFail(String msg) {
        return new JsonResult<E>(-3, null, StringUtils.isEmpty(msg) ? COMMON_ERROR_MESSAGE : msg);
    }

    /**
     * 请求失败消息，根据异常类型，获取不同的提供消息
     *
     * @param throwable 异常
     * @return
     */
    public static <E> JsonResult<E> fail(Throwable throwable) {
        return fail(throwable.getMessage());
    }


    /**
     * 非法请求消息
     *
     * @param msg
     * @return
     */
    public static <E> JsonResult<E> illegal(String msg) {
        return new JsonResult<E>(-1, null, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     * 逻辑处理是否成功
     *
     * @return 是否成功
     */
    @JsonIgnore()
    public boolean isSuccess() {
        return this.code == 0 || this.code == 200;
    }
}
