package com.wangruiguang.client;

import java.lang.reflect.Proxy;

public class ServiceImplFactory {

    public static Object createProxy(Stub stub) {
        Class clz;
        try {
            clz = Class.forName(stub.getInterfaceName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not found");
        }
        return Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, new ServiceInvocationHandler(stub));
    }
}
