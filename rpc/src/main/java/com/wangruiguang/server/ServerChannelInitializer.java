package com.wangruiguang.server;

import com.wangruiguang.common.codec.MessageDecoder;
import com.wangruiguang.common.codec.MessageEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ServerChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MessageDecoder()).addLast(new MessageEncoder()).addLast(new ServerChannelHandler());
    }
}
