package com.wangruiguang.common.registry;

import com.wangruiguang.client.ServerInfo;

public interface Registry {

    void subscribe(String serviceName,String path, DataChangeListener listener);

    void publish(String serviceName, String path, ServerInfo serverInfo);

}
