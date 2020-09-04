package com.wangruiguang.client.channel;

import com.wangruiguang.common.codec.MessageDecoder;
import com.wangruiguang.common.codec.MessageEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ClientChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new MessageEncoder())
                .addLast(new MessageDecoder())
                .addLast(new ClientChannelHandler());
    }
}
