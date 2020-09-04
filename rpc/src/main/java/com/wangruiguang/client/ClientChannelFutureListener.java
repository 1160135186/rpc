package com.wangruiguang.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class ClientChannelFutureListener implements ChannelFutureListener {

    private RequestFuture requestFuture;

    public ClientChannelFutureListener(RequestFuture requestFuture) {
        this.requestFuture = requestFuture;
    }

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {

    }

    public RequestFuture getRequestFuture() {
        return requestFuture;
    }

    public void setRequestFuture(RequestFuture requestFuture) {
        this.requestFuture = requestFuture;
    }
}
