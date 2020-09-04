package com.wangruiguang.common.serialize;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.wangruiguang.common.exception.SerializeFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class HessianSerializer implements Serialization {

    private static final Logger LOGGER = LoggerFactory.getLogger(HessianSerializer.class);

    @Override
    public byte[] serialize(Object object) {
        try {
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            HessianOutput hessianOutput = new HessianOutput(byteOutputStream);
            hessianOutput.writeObject(object);
            return byteOutputStream.toByteArray();
        } catch (Exception e) {
            LOGGER.error("hessian serialize failed.", e);
            throw new SerializeFailedException();
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) {
        try {
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            HessianInput hessianInput = new HessianInput(byteInputStream);
            Object object = hessianInput.readObject();
            return (T) object;
        } catch (Exception e) {
            LOGGER.error("hessian deserialize failed.", e);
            throw new SerializeFailedException();
        }
    }
}
