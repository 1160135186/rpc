package com.wangruiguang.common.exception;

public class RpcException extends RuntimeException{

    private int code;

    private String message;

    public RpcException(int code) {
        this.code = code;
    }

    public RpcException(int code,String message) {
        this.code = code;
        this.message = message;
    }


}
