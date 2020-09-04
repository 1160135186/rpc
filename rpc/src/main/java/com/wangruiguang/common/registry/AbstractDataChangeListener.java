package com.wangruiguang.common.registry;

import com.wangruiguang.client.ServerInfo;
import com.wangruiguang.client.global.GlobalManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractDataChangeListener implements DataChangeListener {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onChange(Registry registry,String serviceName, String path, List<String> providerList) {
        // 前置处理
        preProcess(serviceName, path, providerList);

        // 更新本地服务提供者信息
        updateServerInfoMap(serviceName, path, providerList);

        // 后置处理
        postProcess(registry,serviceName, path, providerList);

    }

    protected void preProcess(String serviceName, String path, List<String> providerList) {

    }

    private void updateServerInfoMap(String serviceName, String path, List<String> providerList) {
        Set<ServerInfo> newServerInfoSet = new HashSet<>(convertStringToServerInfo(providerList));
        String uniqServiceName = serviceName + "_" + path;
        Set<ServerInfo> oldServerInfoSet = new HashSet<>(GlobalManageService.getProvidersByServiceName(uniqServiceName));

        processUpdate(uniqServiceName, newServerInfoSet, oldServerInfoSet);
        processDelete(uniqServiceName, newServerInfoSet, oldServerInfoSet);
    }

    /**
     * 新增或者修改
     */
    private void processUpdate(String uniqServiceName, Set<ServerInfo> newServerInfoSet, Set<ServerInfo> oldServerInfoSet) {
        for (ServerInfo newServerInfo : newServerInfoSet) {
            if (!oldServerInfoSet.contains(newServerInfo)) {
                GlobalManageService.fireUpdateServerInfo(uniqServiceName, newServerInfo);
            }
        }
    }

    /**
     * 删除
     */
    private void processDelete(String uniqServiceName, Set<ServerInfo> newServerInfoSet, Set<ServerInfo> oldServerInfoSet) {
        for (ServerInfo oldServerInfo : oldServerInfoSet) {
            if (!newServerInfoSet.contains(oldServerInfo)) {
                GlobalManageService.fireDeleteServerInfo(uniqServiceName, oldServerInfo);
            }
        }
    }

    private List<ServerInfo> convertStringToServerInfo(List<String> providerList) {
        List<ServerInfo> serverInfoList = new ArrayList<>();
        for (String provider : providerList) {
            serverInfoList.add(ServerInfo.create(provider));
        }
        return serverInfoList;
    }

    protected abstract void postProcess(Registry registry,String serviceName, String path, List<String> providerList);
}
