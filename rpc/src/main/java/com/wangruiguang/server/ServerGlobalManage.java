package com.wangruiguang.server;

import com.wangruiguang.client.ServerInfo;
import com.wangruiguang.common.registry.Registry;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerGlobalManage {

    private static ServerBootstrap bootstrap;

    private static Map<String, Object> serviceProviderMap = new ConcurrentHashMap<>();

    static {
        EventLoopGroup masterGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap().group(masterGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer());
    }

    public static void publishService(ServiceProvider serviceProvider, Registry registry) {
        ServerInfo serverInfo = serviceProvider.getServerInfo();
        String serviceName = serviceProvider.getServiceName();
        String path = serviceProvider.getPath();
        Object instance = serviceProvider.getInstance();

        serviceProviderMap.put(serviceName, instance);
        // 发布
        registry.publish(serviceName, path, serverInfo);
    }

    public static Object getProvider(String serviceName) {
        return serviceProviderMap.get(serviceName);
    }

}
