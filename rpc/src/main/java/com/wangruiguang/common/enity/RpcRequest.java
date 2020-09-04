package com.wangruiguang.common.enity;

import java.io.Serializable;
import java.lang.reflect.Method;

public class RpcRequest implements Serializable {

   public static final long serialVersionUID = -1L;

    /**
     * 请求标志
     */
   private long requestId;

    /**
     * 请求类型
     */
   private int type;

    /**
     * 暴露的接口的名称
     */
    private String interfaceName;

    /**
     * 调用的方法名称
     */
    private String methodName;

    /**
     * 调用的方法参数类型
     */
    private Class[] parameterTypes;

    /**
     * 方法参数
     */
    private Object[] args;

    /**
     * 超时时间
     */
    private int timeout;

    /**
     * 序列化类型
     */
    private int serialType;

    /**
     * 响应
     */
    private Object response;


   public static class RpcRequestBuilder {

        private long requestId;

        private int type;

        private String interfaceName;

        private Method method;

        private Object[] args;

        private int timeout;

        private int serialType;

        private Object response;

        public static RpcRequestBuilder newBuilder() {
            return new RpcRequestBuilder();
        }

        public RpcRequest build() {
            RpcRequest request = new RpcRequest();
            request.setRequestId(requestId);
            request.setType(type);
            request.setInterfaceName(interfaceName);
            request.setMethodName(method.getName());
            request.setParameterTypes(method.getParameterTypes());
            request.setArgs(args);
            request.setTimeout(timeout);
            request.setSerialType(serialType);
            request.setResponse(response);
            return request;
        }

        public RpcRequestBuilder setRequestId(long requestId) {
            this.requestId = requestId;
            return this;
        }

        public RpcRequestBuilder setType(int type) {
            this.type = type;
            return this;
        }

        public RpcRequestBuilder setInterfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
            return this;
        }

        public RpcRequestBuilder setMethod(Method method) {
            this.method = method;
            return this;
        }


        public RpcRequestBuilder setArgs(Object[] args) {
            this.args = args;
            return this;
        }

        public RpcRequestBuilder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public RpcRequestBuilder setSerialType(int serialType) {
            this.serialType = serialType;
            return this;
        }

        public RpcRequestBuilder setResponse(Object response) {
            this.response = response;
            return this;
        }
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public int getSerialType() {
        return serialType;
    }

    public void setSerialType(int serialType) {
        this.serialType = serialType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }
}
