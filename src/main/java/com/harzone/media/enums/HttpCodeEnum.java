package com.harzone.media.enums;

/**
 * Describe: http 状态码
 * 
 */
public enum HttpCodeEnum {

    SUCCESS(200, "操作成功"),
    
//    INVALID_REQUEST(400, "参数错误"),
//    UNAUTHORIZED(401, "没有权限"),
//    FORBIDDEN(403, "禁止访问"),
//    NOT_FOUND(404, "资源不存在"),
//    NOT_ACCEPTABLE(406, "请求的格式不正确"),
//    GONE(410, "数据被删除"),
//    UNPROCESABLE_ENTITY(422, "参数验证错误"),
//    INTERNAL_SERVER_ERROR(500, "服务器发生错误"),
//    UNKNOWN_ERROR(500, "未知错误"),
//    FAIL(501, "操作失败"),

    SYSTEM_ERR(1000, "系统错误"),
    
    PARAM_INVALID_ERR(10001, "参数无效"),
    PARAM_EMPTY_ERR(10002, "参数为空"),
    PARAM_TYPE_ERR(10003, "参数类型错误"),
    PARAM_MISS_ERR(10004, "参数缺失"),

    ACCOUNT_NOT_LOGIN_ERR(20001, "用户未登录"),
    ACCOUNT_OR_PASSWORD_ERR(20002, "账号或密码错误"),
    ACCOUNT_DISABLED_ERR(20003, "账号已被禁用"),
    ACCOUNT_NOT_EXIST_ERR(20004, "用户不存在"),
    ACCOUNT_EXISTS_ERR(20005, "用户已存在"),
    
    API_TOKEN_EMPTY_ERR(30001, "API token为空"),
    API_TOKEN_INVALID_ERR(30002, "API token无效"),
    API_TOKEN_EXPIRE_ERR(30003, "API token过期"),
	
	USER_TOKEN_EMPTY_ERR(31001, "用户token为空"),
	USER_TOKEN_INVALID_ERR(31002, "用户token无效"),
	USER_TOKEN_EXPIRE_ERR(31003, "用户token过期"),
	
	SIGN_EMPTY_ERR(40001, "签名参数为空"),
	SIGN_VERIFY_ERR(40002, "验签失败"),
	
	TIMESTAMP_EMPTY_ERR(41001, "请求时间为空"),
	TIMESTAMP_FORMAT_ERR(41002, "请求时间格式不正确"),
	TIMESTAMP_EXPIRE_ERR(41003, "请求时间过期"),
	
	NONCE_EMPTY_ERR(42001, "随机字符串为空"),
	NONCE_REPEAT_ERR(42002, "接口重复请求"),
	
	APPID_EMPTY_ERR(43001, "appId为空"),
	
	REQUEST_EXCEED_LIMIT(50001, "请求过于频繁，请稍后再试"),
    
    REQUEST_TIME_OUT(504,"请求超时");

    private final int code;
    
    private final String msg;

    HttpCodeEnum(final int code, final String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
