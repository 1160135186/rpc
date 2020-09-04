package com.wangruiguang.client.channel;

import com.wangruiguang.client.RequestFuture;
import com.wangruiguang.client.global.GlobalManageService;
import com.wangruiguang.common.constant.RequestEnum;
import com.wangruiguang.common.enity.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientChannelHandler extends SimpleChannelInboundHandler {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof RpcRequest)) {
            return;
        }

        RpcRequest request = (RpcRequest) msg;
        if (request.getType() == RequestEnum.RESPONSE.getType()) {
            RequestFuture requestFuture = GlobalManageService.getRequestFuture(String.valueOf(request.getRequestId()));
            if (request.getResponse() instanceof Exception) {
                requestFuture.setSuccess(false);
                requestFuture.setError(request.getResponse());
            } else {
                requestFuture.setSuccess(true);
                requestFuture.setResult(request.getResponse());
            }
            requestFuture.complete();
        }
    }
}
