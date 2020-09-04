package com.wangruiguang.server;

import com.wangruiguang.common.codec.MessageDecoder;
import com.wangruiguang.common.codec.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class RpcServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);

	private String path;

	private int port;

	/**
	 * 存放service name和service实例的映射
	 */
	public static Map<String,Object> serviceMap = new ConcurrentHashMap<>(16);

	public static void register(String name,Object object) {
		serviceMap.put(name,object);
	}

	public RpcServer() {
	}

	public RpcServer(int port) {
		this.port = port;
	}

	public void init() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						socketChannel.pipeline()
								.addLast(new MessageDecoder())
								.addLast(new MessageEncoder())
								.addLast(new ServerChannelHandler());
					}
				});

		try {
			ChannelFuture future = serverBootstrap.bind(port).sync();
		} catch (Exception e) {
			LOGGER.error("server bootstrap failed",e);
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		new RpcServer(10010).init();
	}
}
