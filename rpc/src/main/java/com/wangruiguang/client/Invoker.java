package com.wangruiguang.client;

import com.wangruiguang.common.enity.RpcRequest;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class Invoker {

    private static Map<String, Channel> serviceChannelMap = new HashMap<>(16);

    public static Map<Long,RequestFuture> requestFutureMap = new HashMap<>(16);

    public static RequestFuture invoke(RpcRequest request) {
        Channel channel = serviceChannelMap.get(request.getInterfaceName());
        if (channel == null) {
            throw new RuntimeException("service provider not found");
        }

        RequestFuture requestFuture = new RequestFuture();
        requestFutureMap.put(request.getRequestId(),requestFuture);
        channel.writeAndFlush(request);
        return requestFuture;
    }

    public static void registerChannel(String serviceName,Channel channel) {
        serviceChannelMap.put(serviceName,channel);
    }
}
