package com.wangruiguang.server;

import com.wangruiguang.common.constant.RequestEnum;
import com.wangruiguang.common.enity.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerChannelHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof RpcRequest) {
            RpcRequest request = (RpcRequest) msg;
            Object service = ServerGlobalManage.getProvider(request.getInterfaceName());
            if (service == null) {
                LOGGER.error("no service found for name [{}]", request.getInterfaceName());
                return;
            }

            Method method = service.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
            if (method == null) {
                LOGGER.error("no such method.service:[{}],method:[{}]", request.getInterfaceName(), request.getMethodName());
                return;
            }

            try {
                Object result = method.invoke(service, request.getArgs());
                request.setResponse(result);
            } catch (Exception e) {
                // todo 答应ip之类的信息
                LOGGER.error("invoke method error.requestId={}|method={}", request.getRequestId(), method.getName(), e);
                request.setResponse(e);
            }
            request.setType(RequestEnum.RESPONSE.getType());
            ctx.channel().writeAndFlush(request);
        }
    }

}
