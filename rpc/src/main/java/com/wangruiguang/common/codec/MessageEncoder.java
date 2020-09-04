package com.wangruiguang.common.codec;

import com.sun.tools.javac.util.Assert;
import com.wangruiguang.common.enity.RpcRequest;
import com.wangruiguang.common.serialize.HessianSerializer;
import com.wangruiguang.common.serialize.SerializerEnum;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<RpcRequest> {

    private HessianSerializer hessianSerializer;

    public MessageEncoder() {
        this.hessianSerializer = new HessianSerializer();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcRequest msg, ByteBuf out) throws Exception {
        SerializerEnum serializerEnum = SerializerEnum.fromInt(msg.getSerialType());
        Assert.checkNonNull(serializerEnum, "illegal serial type");

        int length;
        byte[] body;
        switch (serializerEnum) {
            case HESSIAN:
                body = hessianSerializer.serialize(msg);
                length = body.length;
                out.writeInt(msg.getSerialType());
                out.writeInt(length);
                out.writeBytes(body);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + serializerEnum);
        }
    }
}
