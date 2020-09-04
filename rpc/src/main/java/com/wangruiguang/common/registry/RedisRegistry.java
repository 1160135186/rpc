package com.wangruiguang.common.registry;

import com.wangruiguang.client.ServerInfo;

public class RedisRegistry implements Registry {


    @Override
    public void subscribe(String serviceName, String path, DataChangeListener listener) {

    }

    @Override
    public void publish(String serviceName, String path, ServerInfo serverInfo) {

    }
}
