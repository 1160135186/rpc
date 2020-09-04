package com.wangruiguang.common.constant;

public enum RequestEnum {
    /**
     * 调用
     */
    CALL(1),

    /**
     * 响应
     */
    RESPONSE(2)
    ;

    private int type;

    RequestEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
