package com.wangruiguang.server;

import com.wangruiguang.client.ServerInfo;

/**
 * 服务提供者
 */
public class ServiceProvider {

    /**
     * 地址 端口 协议等信息
     */
    private ServerInfo serverInfo;

    /**
     * 实例
     */
    private Object instance;

    /**
     * 名称
     * @return
     */
    private String serviceName;

    private String path;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
