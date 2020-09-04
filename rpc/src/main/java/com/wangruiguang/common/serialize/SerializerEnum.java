package com.wangruiguang.common.serialize;

public enum SerializerEnum {
    /**
     * hessian序列化
     */
    HESSIAN(1)
    ;

    private int type;

    SerializerEnum(int type) {
        this.type = type;
    }

    public static SerializerEnum fromInt(int type) {
        for (SerializerEnum serializerEnum : SerializerEnum.values()) {
            if (serializerEnum.getType() == type) {
                return serializerEnum;
            }
        }

        return null;
    }

    public int getType() {
        return type;
    }
}
