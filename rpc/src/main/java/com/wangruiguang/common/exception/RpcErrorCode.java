package com.wangruiguang.common.exception;

public interface RpcErrorCode {

    /**
     * 未定义异常
     */
    int UN_DEFINED = 0;

    /**
     * 没有服务提供者
     */
    int NOT_PROVIDER_FOUND = -1;

    /**
     * 没有channel
     */
    int NO_ACTIVE_CHANNEL = -2;

    /**
     * 超时
     */
    int TIME_OUT = -3;



}
