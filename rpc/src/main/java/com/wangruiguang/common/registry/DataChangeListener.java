package com.wangruiguang.common.registry;

import java.util.List;

/**
 * 用于监听服务提供者的变化
 */
public interface DataChangeListener {

    void onChange(Registry registry, String serviceName, String path, List<String> providers);
}
