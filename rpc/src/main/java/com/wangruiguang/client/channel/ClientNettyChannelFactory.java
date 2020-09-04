package com.wangruiguang.client.channel;

import com.wangruiguang.client.ServerInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;

public class ClientNettyChannelFactory {

    private static Bootstrap bootstrap = new Bootstrap();

    public static Channel createChannel(ServerInfo serverInfo) {
        try {
            return StringUtils.equals(serverInfo.getProtocol(), "udp") ? createUdpChannel(serverInfo) : createTcpChannel(serverInfo);
        } catch (InterruptedException e) {
            return null;
        }
    }

    private static Channel createUdpChannel(ServerInfo serverInfo) throws InterruptedException {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        return bootstrap.group(workGroup)
                .channel(NioDatagramChannel.class)
                .handler(new ClientChannelInitializer())
                .connect(serverInfo.getHost(),serverInfo.getPort()).sync().channel();
    }

    private static Channel createTcpChannel(ServerInfo serverInfo) throws InterruptedException {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        return bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer())
                .connect(serverInfo.getHost(), serverInfo.getPort()).sync().channel();

    }
}
