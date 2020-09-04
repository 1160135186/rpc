package com.wangruiguang.common.serialize;

/**
 * 序列化接口
 */
public interface Serialization {

    byte[] serialize(Object object);

    <T> T deserialize(byte[] bytes,Class<T> clz);
}
