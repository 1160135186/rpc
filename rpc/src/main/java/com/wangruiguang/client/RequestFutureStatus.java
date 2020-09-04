package com.wangruiguang.client;

public enum RequestFutureStatus {
    /**
     * 新建
     */
    NEW(0),

    /**
     * 完成
     */
    COMPLETE(1)
    ;


    int status;

    RequestFutureStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
