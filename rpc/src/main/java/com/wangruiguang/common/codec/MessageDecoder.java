package com.wangruiguang.common.codec;

import com.wangruiguang.common.enity.RpcRequest;
import com.wangruiguang.common.serialize.HessianSerializer;
import com.wangruiguang.common.serialize.SerializerEnum;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    private HessianSerializer hessianSerializer;

    public MessageDecoder() {
        this.hessianSerializer = new HessianSerializer();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int type = in.readInt();
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        if (type == SerializerEnum.HESSIAN.getType()) {
            RpcRequest request = hessianSerializer.deserialize(bytes, RpcRequest.class);
            out.add(request);
        }
    }
}
