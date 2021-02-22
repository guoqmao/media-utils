package com.harzone.media.enums;

public enum BusinessCodeEnum {

	SIGN_ERROR(50001, "签名错误"),
    BALANCE_NOT_ENOUGH(50002, "余额不足");

    private final int code;
    
    private final String message;

    BusinessCodeEnum(final int code, final String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
